package com.example.security.service;

import com.example.security.domain.entity.UserEntity;
import com.example.security.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
