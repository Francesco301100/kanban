package com.example.kanban.board;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepo extends JpaRepository<Board, Long> {

    List<Board> findBoardsByUserId(Long userId);

}
