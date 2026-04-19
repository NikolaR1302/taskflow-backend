package com.taskflow.taskflow_backend.controller;

import com.taskflow.taskflow_backend.dto.ProjectRequest;
import com.taskflow.taskflow_backend.dto.ProjectResponse;
import com.taskflow.taskflow_backend.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectRequest request, Principal principal) {
        return ResponseEntity.ok(projectService.createProject(request, principal.getName()));
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getMyProjects(Principal principal){
        return ResponseEntity.ok(projectService.getProjectByUser(principal.getName()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long id, @RequestBody ProjectRequest request){
        return ResponseEntity.ok(projectService.updateProject(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id){
        projectService.deleteProject(id);
        return ResponseEntity.ok().build();
    }

}
