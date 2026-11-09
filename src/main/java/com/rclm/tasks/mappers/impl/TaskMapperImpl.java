package com.rclm.tasks.mappers.impl;

import org.springframework.stereotype.Component;

import com.rclm.tasks.domain.dto.TaskDTO;
import com.rclm.tasks.domain.entities.Task;
import com.rclm.tasks.mappers.TaskMapper;

@Component
public class TaskMapperImpl implements TaskMapper {
  @Override
  public Task fromDTO(TaskDTO taskDTO) {
    return(new Task(
      taskDTO.id(),
      taskDTO.title(),
      taskDTO.description(),
      taskDTO.due(),
      null,
      taskDTO.status(),
      taskDTO.priority(),
      null
    ));
  }

  @Override
  public TaskDTO toDTO(Task task) {
    return(new TaskDTO(
      task.getId(),
      task.getTitle(),
      task.getDescription(),
      task.getDue(),
      task.getStatus(),
      task.getPriority()
    ));
  }
}
