package com.taskflow.taskflow_backend.service;

import com.taskflow.taskflow_backend.dto.UserResponse;
import com.taskflow.taskflow_backend.model.User;
import com.taskflow.taskflow_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }

    public UserResponse getUserByEmail(String email){
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }

}
