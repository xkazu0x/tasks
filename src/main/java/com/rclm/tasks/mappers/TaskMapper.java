package com.rclm.tasks.mappers;

import com.rclm.tasks.domain.dto.TaskDTO;
import com.rclm.tasks.domain.entities.Task;

public interface TaskMapper {
  Task fromDTO(TaskDTO taskDTO);
  TaskDTO toDTO(Task task);
}
