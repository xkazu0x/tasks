package com.rclm.tasks.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.rclm.tasks.domain.entities.Task;
import com.rclm.tasks.domain.entities.TaskList;
import com.rclm.tasks.domain.entities.TaskPriority;
import com.rclm.tasks.domain.entities.TaskStatus;
import com.rclm.tasks.repositories.TaskListRepository;
import com.rclm.tasks.repositories.TaskRepository;
import com.rclm.tasks.services.TaskService;

import jakarta.transaction.Transactional;

@Service
public class TaskServiceImpl implements TaskService {
  private final TaskRepository taskRepository;
  private final TaskListRepository taskListRepository;

  public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
    this.taskRepository = taskRepository;
    this.taskListRepository = taskListRepository;
  }

  @Override
  public List<Task> listTasks(UUID taskListId) {
    return(taskRepository.findByTaskListId(taskListId));
  }

  @Transactional
  @Override
  public Task createTask(UUID taskListId, Task task) {
    if (task.getId() != null) {
      throw new IllegalArgumentException("Task already has an ID.");
    }
    if (task.getTitle() == null || task.getTitle().isBlank()) {
      throw new IllegalArgumentException("Task must have a title.");
    }

    LocalDateTime created = LocalDateTime.now();
    TaskStatus taskStatus = TaskStatus.OPEN;
    TaskPriority taskPriority = Optional.ofNullable(task.getPriority()).orElse(TaskPriority.LOW);
    TaskList taskList = taskListRepository.findById(taskListId).orElseThrow(() -> new IllegalArgumentException("Invalid Task List ID provided."));

    return(taskRepository.save(new Task(
      null,
      task.getTitle(),
      task.getDescription(),
      task.getDue(),
      created,
      taskStatus,
      taskPriority,
      taskList
    )));
  }

  @Override
  public Optional<Task> getTask(UUID taskListId, UUID taskId) {
    return(taskRepository.findByTaskListIdAndId(taskListId, taskId));
  }

  @Transactional
  @Override
  public Task updateTask(UUID taskListId, UUID taskId, Task task) {
    if (task.getId() == null) {
      throw new IllegalArgumentException("Task must have an ID.");
    }
    if (!Objects.equals(task.getId(), taskId)) {
      throw new IllegalArgumentException("Task IDs do not match.");
    }
    if (task.getStatus() == null) {
      throw new IllegalArgumentException("Task must have a valid status.");
    }
    if (task.getPriority() == null) {
      throw new IllegalArgumentException("Task must have a valid priority.");
    }

    Task t = taskRepository.findByTaskListIdAndId(taskListId, taskId).orElseThrow(() -> new IllegalArgumentException("Task not found."));
    t.setTitle(task.getTitle());
    t.setDescription(task.getDescription());
    t.setDue(task.getDue());
    t.setStatus(task.getStatus());
    t.setPriority(task.getPriority());

    return(taskRepository.save(t));
  }

  @Transactional
  @Override
  public void deleteTask(UUID taskListId, UUID taskId) {
    taskRepository.deleteByTaskListIdAndId(taskListId, taskId);
  }
}
