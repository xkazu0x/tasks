package com.rclm.tasks.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rclm.tasks.domain.dto.TaskDTO;
import com.rclm.tasks.mappers.TaskMapper;
import com.rclm.tasks.services.TaskService;

@RestController
@RequestMapping(path = "/task-lists/{task_list_id}/tasks")
public class TaskController {
  private final TaskService taskService;
  private final TaskMapper taskMapper;

  public TaskController(TaskService taskService, TaskMapper taskMapper) {
    this.taskService = taskService;
    this.taskMapper = taskMapper;
  }

  @GetMapping
  public List<TaskDTO> listTasks(@PathVariable("task_list_id") UUID taskListId) {
    return(taskService.listTasks(taskListId).stream().map(taskMapper::toDTO).toList());
  }

  @PostMapping
  public TaskDTO createTask(@PathVariable("task_list_id") UUID taskListId, @RequestBody TaskDTO taskDTO) {
    return(taskMapper.toDTO(taskService.createTask(taskListId, taskMapper.fromDTO(taskDTO))));
  }

  @GetMapping(path = "/{task_id}")
  public Optional<TaskDTO> getTask(@PathVariable("task_list_id") UUID taskListId, @PathVariable("task_id") UUID taskId) {
    return(taskService.getTask(taskListId, taskId).map(taskMapper::toDTO));
  }

  @PutMapping(path = "/{task_id}")
  public TaskDTO updateTask(@PathVariable("task_list_id") UUID taskListId, @PathVariable("task_id") UUID taskId, @RequestBody TaskDTO taskDTO) {
    return(taskMapper.toDTO(taskService.updateTask(taskListId, taskId, taskMapper.fromDTO(taskDTO))));
  }

  @DeleteMapping(path = "/{task_id}")
  public void deleteTask(@PathVariable("task_list_id") UUID taskListId, @PathVariable("task_id") UUID taskId) {
    taskService.deleteTask(taskListId, taskId);
  }
}
