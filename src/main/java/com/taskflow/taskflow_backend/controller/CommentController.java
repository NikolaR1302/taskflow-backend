package com.taskflow.taskflow_backend.controller;

import com.taskflow.taskflow_backend.dto.CommentRequest;
import com.taskflow.taskflow_backend.dto.CommentResponse;
import com.taskflow.taskflow_backend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponse> addComment(@RequestBody CommentRequest request, Principal principal){
        return ResponseEntity.ok(commentService.addComment(request, principal.getName()));
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<CommentResponse>> getCommentsByTask(@PathVariable Long taskId){
        return ResponseEntity.ok(commentService.getCommentsByTask(taskId));
    }


}
