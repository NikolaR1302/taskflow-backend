package com.taskflow.taskflow_backend.service;

import com.taskflow.taskflow_backend.dto.ProjectRequest;
import com.taskflow.taskflow_backend.dto.ProjectResponse;
import com.taskflow.taskflow_backend.model.Project;
import com.taskflow.taskflow_backend.model.User;
import com.taskflow.taskflow_backend.repository.ProjectRepository;
import com.taskflow.taskflow_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectResponse createProject(ProjectRequest request, String email){
        User owner = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        Project project = new Project();
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setOwner(owner);

        projectRepository.save(project);
        return mapToResponse(project);
    }

    public List<ProjectResponse> getProjectByUser(String email){
        User owner = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));

        return projectRepository.findByOwnerId(owner.getId()).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public ProjectResponse updateProject(Long id, ProjectRequest request){
        Project project = projectRepository.findById(id).orElseThrow(()-> new RuntimeException("Project not found"));
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        projectRepository.save(project);
        return mapToResponse(project);
    }

    public void deleteProject(Long id){
        projectRepository.deleteById(id);
    }



    private ProjectResponse mapToResponse(Project project) {
        return new ProjectResponse(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getOwner().getUsername(),
                project.getCreatedAt()
        );
    }

}
