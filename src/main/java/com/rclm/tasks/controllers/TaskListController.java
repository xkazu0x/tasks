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

import com.rclm.tasks.domain.dto.TaskListDTO;
import com.rclm.tasks.domain.entities.TaskList;
import com.rclm.tasks.mappers.TaskListMapper;
import com.rclm.tasks.services.TaskListService;

@RestController
@RequestMapping(path = "/task-lists")
public class TaskListController {
  private final TaskListService taskListService;
  private final TaskListMapper taskListMapper;

  public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
    this.taskListService = taskListService;
    this.taskListMapper = taskListMapper;
  }

  @GetMapping
  public List<TaskListDTO> listTaskLists() {
    return(taskListService.listTaskLists().stream().map(taskListMapper::toDTO).toList());
  }

  @PostMapping
  public TaskListDTO createTaskList(@RequestBody TaskListDTO taskListDTO) {
    return(taskListMapper.toDTO(taskListService.createTaskList(taskListMapper.fromDTO(taskListDTO))));
  }

  @GetMapping(path = "/{task_list_id}")
  public Optional<TaskListDTO> getTaskList(@PathVariable("task_list_id") UUID taskListId) {
    return(taskListService.getTaskList(taskListId).map(taskListMapper::toDTO));
  }

  @PutMapping(path = "/{task_list_id}")
  public TaskListDTO updateTaskList(@PathVariable("task_list_id") UUID id, @RequestBody TaskListDTO taskListDTO) {
    return(taskListMapper.toDTO(taskListService.updateTaskList(id, taskListMapper.fromDTO(taskListDTO))));
  }

  @DeleteMapping(path = "/{task_list_id}")
  public void deleteTaskList(@PathVariable("task_list_id") UUID id) {
    taskListService.deleteTaskList(id);
  }
}
