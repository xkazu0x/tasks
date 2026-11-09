package com.rclm.tasks.mappers.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.rclm.tasks.domain.dto.TaskListDTO;
import com.rclm.tasks.domain.entities.Task;
import com.rclm.tasks.domain.entities.TaskList;
import com.rclm.tasks.domain.entities.TaskStatus;
import com.rclm.tasks.mappers.TaskListMapper;
import com.rclm.tasks.mappers.TaskMapper;

@Component
public class TaskListMapperImpl implements TaskListMapper{
  private final TaskMapper taskMapper;

  public TaskListMapperImpl(TaskMapper taskMapper) {
    this.taskMapper = taskMapper;
  }

  @Override
  public TaskList fromDTO(TaskListDTO taskListDTO) {
    return(new TaskList(
      taskListDTO.id(),
      taskListDTO.title(),
      taskListDTO.description(),
      null,
      Optional.ofNullable(taskListDTO.tasks()).map(tasks -> tasks.stream().map(taskMapper::fromDTO).toList()).orElse(null))
    );
  }

  @Override
  public TaskListDTO toDTO(TaskList taskList) {
    return(new TaskListDTO(
      taskList.getId(),
      taskList.getTitle(),
      taskList.getDescription(),
      Optional.ofNullable(taskList.getTasks()).map(List::size).orElse(0),
      calculateTaskListProgress(taskList.getTasks()),
      Optional.ofNullable(taskList.getTasks()).map(tasks -> tasks.stream().map(taskMapper::toDTO).toList()).orElse(null)
    ));
  }

  private Double calculateTaskListProgress(List<Task> tasks) {
    if (tasks == null) return null;
    long closedTaskCount = tasks.stream().filter(task -> task.getStatus() == TaskStatus.CLOSED).count();
    return((double)closedTaskCount/tasks.size());
  }
}
