package com.example.kanban.task;

import com.example.kanban.board.Board;
import com.example.kanban.board.BoardRepo;
import com.example.kanban.exceptions.BoardNotFoundException;
import com.example.kanban.exceptions.NoTaskFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepo taskRepo;
    private final BoardRepo boardRepo;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskService(TaskRepo taskRepo, BoardRepo boardRepo, TaskMapper taskMapper) {
        this.taskRepo = taskRepo;
        this.boardRepo = boardRepo;
        this.taskMapper = taskMapper;
    }

    public List<TaskDTO> getAllTasks() {
        if (taskRepo.findAll().isEmpty()) {
            return Collections.emptyList();
        }
        return taskRepo.findAll().stream()
                .map(taskMapper::toDto)
                .toList();
    }

    public TaskDTO getTaskById(Long id) {
        if (taskRepo.findById(id).isEmpty()) {
            throw new NoTaskFoundException("Board with id " + id + " not found");
        }
        return taskRepo.findById(id)
                .map(taskMapper::toDto)
                .orElseThrow(() -> new NoTaskFoundException("Board with id " + id + " not found"));
    }

    public List<TaskDTO> getTasksByStatus(TaskStatus status) {
        List<Task> tasks = taskRepo.findTasksByStatus(status);
        if (tasks.isEmpty()) {
            throw new NoTaskFoundException("Keine Aufgaben mit dem Status " + status + " gefunden");
        }
        return tasks.stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }


    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);
        Board board = boardRepo.findById(taskDTO.boardId())
                .orElseThrow(() -> new BoardNotFoundException("Board not found"));
        task.setBoard(board);
        taskRepo.save(task);
        return taskMapper.toDto(task);
    }

    public void deleteTask(Long id) {
        if (taskRepo.findById(id).isEmpty()) {
            throw new NoTaskFoundException("Board with id " + id + " not found");
        }
        taskRepo.deleteById(id);
    }

}
