package com.rclm.tasks.domain.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "due")
  private LocalDateTime due;

  @Column(name = "created", nullable = false)
  private LocalDateTime created;

  @Column(name = "status", nullable = false)
  private TaskStatus status;

  @Column(name = "priority", nullable = false)
  private TaskPriority priority;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "task_list_id")
  private TaskList taskList;

  public Task() {
  }

  public Task(UUID id, String title, String description, LocalDateTime due, LocalDateTime created, TaskStatus status,
      TaskPriority priority, TaskList taskList) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.due = due;
    this.created = created;
    this.status = status;
    this.priority = priority;
    this.taskList = taskList;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDateTime getDue() {
    return due;
  }

  public void setDue(LocalDateTime due) {
    this.due = due;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public TaskStatus getStatus() {
    return status;
  }

  public void setStatus(TaskStatus status) {
    this.status = status;
  }

  public TaskPriority getPriority() {
    return priority;
  }

  public void setPriority(TaskPriority priority) {
    this.priority = priority;
  }

  public TaskList getTaskList() {
    return taskList;
  }

  public void setTaskList(TaskList taskList) {
    this.taskList = taskList;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    result = prime * result + ((description == null) ? 0 : description.hashCode());
    result = prime * result + ((due == null) ? 0 : due.hashCode());
    result = prime * result + ((created == null) ? 0 : created.hashCode());
    result = prime * result + ((status == null) ? 0 : status.hashCode());
    result = prime * result + ((priority == null) ? 0 : priority.hashCode());
    result = prime * result + ((taskList == null) ? 0 : taskList.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Task other = (Task) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals(other.description))
      return false;
    if (due == null) {
      if (other.due != null)
        return false;
    } else if (!due.equals(other.due))
      return false;
    if (created == null) {
      if (other.created != null)
        return false;
    } else if (!created.equals(other.created))
      return false;
    if (status != other.status)
      return false;
    if (priority != other.priority)
      return false;
    if (taskList == null) {
      if (other.taskList != null)
        return false;
    } else if (!taskList.equals(other.taskList))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Task [id=" + id + ", title=" + title + ", description=" + description + ", due=" + due + ", created="
        + created + ", status=" + status + ", priority=" + priority + ", taskList=" + taskList + "]";
  }
}
