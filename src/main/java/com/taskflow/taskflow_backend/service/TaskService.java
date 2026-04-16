package com.taskflow.taskflow_backend.service;


import com.taskflow.taskflow_backend.dto.TaskRequest;
import com.taskflow.taskflow_backend.dto.TaskResponse;
import com.taskflow.taskflow_backend.model.Project;
import com.taskflow.taskflow_backend.model.Task;
import com.taskflow.taskflow_backend.model.User;
import com.taskflow.taskflow_backend.repository.ProjectRepository;
import com.taskflow.taskflow_backend.repository.TaskRepository;
import com.taskflow.taskflow_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    private TaskResponse mapToResponse(Task task){
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getProject().getId(),
                task.getAssignedTo() != null? task.getAssignedTo().getUsername() : null,
                task.getDueDate(),
                task.getCreatedAt()
        );
    }

    public TaskResponse createTask(TaskRequest request){
        Project project = projectRepository.findById(request.getProjectId()).
                orElseThrow(() -> new RuntimeException("Project not found"));

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setProject(project);
        task.setDueDate(request.getDueDate());

        if(request.getAssignedToId() != null) {
            User assignetTo = userRepository.findById(request.getAssignedToId()).
                    orElseThrow(()-> new RuntimeException("User not found"));
            task.setAssignedTo(assignetTo);
        }

        taskRepository.save(task);
        return mapToResponse(task);
    }

    public List<TaskResponse> getTasksByProject(Long projectId){
        return taskRepository.findByProjectId(projectId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public TaskResponse updateTask(Long id, TaskRequest request){
        Task task = taskRepository.findById(id).
                orElseThrow(()-> new RuntimeException("Task not found"));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setDueDate(request.getDueDate());

        if(request.getAssignedToId() != null) {
            User assignetTo = userRepository.findById(request.getAssignedToId())
                    .orElseThrow(()-> new RuntimeException("User not found"));
            task.setAssignedTo(assignetTo);
        }

        taskRepository.save(task);
        return mapToResponse(task);
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

}
