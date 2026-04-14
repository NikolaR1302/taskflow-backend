package com.taskflow.taskflow_backend.repository;

import com.taskflow.taskflow_backend.model.TaskAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment, Long> {
    List<TaskAssignment> findByTaskId(Long taskId);
    List<TaskAssignment> findByUserId(Long userId);
}
