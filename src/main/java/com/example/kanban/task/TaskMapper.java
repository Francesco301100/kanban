package com.example.kanban.task;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(dto.createdAt() != null ? dto.createdAt() : LocalDateTime.now())")
    Task toEntity(TaskDTO dto);

    TaskDTO toDto(Task entity);
}
