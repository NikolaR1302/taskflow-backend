package com.taskflow.taskflow_backend.dto;

import lombok.Data;

@Data
public class CommentRequest {
    private String content;
    private Long taskId;
}
