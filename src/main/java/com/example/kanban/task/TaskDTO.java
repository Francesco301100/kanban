package com.example.kanban.task;

import java.time.LocalDateTime;

public record TaskDTO(Long id, String title, String description, TaskStatus status, LocalDateTime createdAt, Long boardId) {
}
