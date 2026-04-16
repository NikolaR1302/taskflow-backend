package com.taskflow.taskflow_backend.service;


import com.taskflow.taskflow_backend.dto.CommentRequest;
import com.taskflow.taskflow_backend.dto.CommentResponse;
import com.taskflow.taskflow_backend.model.Comment;
import com.taskflow.taskflow_backend.model.Task;
import com.taskflow.taskflow_backend.model.User;
import com.taskflow.taskflow_backend.repository.CommentRepository;
import com.taskflow.taskflow_backend.repository.TaskRepository;
import com.taskflow.taskflow_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public CommentResponse addComment(CommentRequest request, String email){
        Task task = taskRepository.findById(request.getTaskId()).
                orElseThrow(()-> new RuntimeException("Task not found"));

        User user = userRepository.findByEmail(email).
                orElseThrow(()-> new RuntimeException("User not found"));

        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setTask(task);
        comment.setUser(user);

        commentRepository.save(comment);
        return mapToResponse(comment);
    }

    public List<CommentResponse> getCommentsByTask(Long taskId){
        return commentRepository.findByTaskId(taskId).
                stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private CommentResponse mapToResponse(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getUser().getUsername(),
                comment.getTask().getId(),
                comment.getCreatedAt()
        );
    }

}
