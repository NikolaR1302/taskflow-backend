package com.taskflow.taskflow_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommentResponse {
    private Long id;
    private String content;
    private String username;
    private Long taskId;
    private LocalDateTime createdAt;
}
