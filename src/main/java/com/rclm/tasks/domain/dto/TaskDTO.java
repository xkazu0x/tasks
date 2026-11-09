package com.rclm.tasks.domain.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.rclm.tasks.domain.entities.TaskPriority;
import com.rclm.tasks.domain.entities.TaskStatus;

public record TaskDTO(
  UUID id,
  String title,
  String description,
  LocalDateTime due,
  TaskStatus status,
  TaskPriority priority
) {
}
