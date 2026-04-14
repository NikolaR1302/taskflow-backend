package com.taskflow.taskflow_backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskRequest {
    private String title;
    private String description;
    private String status;
    private Long projectId;
    private Long assignedToId;
    private LocalDateTime dueDate;
}
