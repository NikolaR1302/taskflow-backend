package com.taskflow.taskflow_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private String status;
    private Long projectId;
    private String assignedToUsername;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
}
