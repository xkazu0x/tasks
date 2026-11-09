package com.rclm.tasks.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.rclm.tasks.domain.entities.TaskList;
import com.rclm.tasks.repositories.TaskListRepository;
import com.rclm.tasks.services.TaskListService;

import jakarta.transaction.Transactional;

@Service
public class TaskListServiceImpl implements TaskListService {
  private final TaskListRepository taskListRepository;

  public TaskListServiceImpl(TaskListRepository taskListRepository) {
    this.taskListRepository = taskListRepository;
  }

  @Override
  public List<TaskList> listTaskLists() {
    return(taskListRepository.findAll());
  }

  @Override
  public TaskList createTaskList(TaskList taskList) {
    if (taskList.getId() != null) {
      throw new IllegalArgumentException("Task list already has an ID.");
    }
    if (taskList.getTitle() == null || taskList.getTitle().isBlank()) {
      throw new IllegalArgumentException("Task list title must be present.");
    }

    LocalDateTime created = LocalDateTime.now();

    return(taskListRepository.save(new TaskList(
      null,
      taskList.getTitle(),
      taskList.getDescription(),
      created,
      null
    )));
  }

  @Override
  public Optional<TaskList> getTaskList(UUID id) {
    return(taskListRepository.findById(id));
  }

  @Transactional
  @Override
  public TaskList updateTaskList(UUID id, TaskList taskList) {
    if (taskList.getId() == null) {
      throw new IllegalArgumentException("Task List must have an ID.");
    }
    if (!Objects.equals(taskList.getId(), id)) {
      throw new IllegalArgumentException("Task List IDs do not match.");
    }

    TaskList tl = taskListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task List not found."));
    tl.setTitle(taskList.getTitle());
    tl.setDescription(taskList.getDescription());

    return(taskListRepository.save(tl));
  }

  @Override
  public void deleteTaskList(UUID id) {
    taskListRepository.deleteById(id);
  }
}
