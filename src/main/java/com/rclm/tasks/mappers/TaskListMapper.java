package com.rclm.tasks.mappers;

import com.rclm.tasks.domain.dto.TaskListDTO;
import com.rclm.tasks.domain.entities.TaskList;

public interface TaskListMapper {
  TaskList fromDTO(TaskListDTO taskListDTO);
  TaskListDTO toDTO(TaskList taskList);
}
