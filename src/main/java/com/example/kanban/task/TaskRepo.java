package com.example.kanban.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {


    List<Task> findTasksByStatus(TaskStatus status);

}
