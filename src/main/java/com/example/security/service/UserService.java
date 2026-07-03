package com.example.security.service;

import com.example.security.domain.entity.UserEntity;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    public UserEntity getUserFromUsername(String username){
       return userRepository
                .findByUsernameAndIsActive(username, true)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       UserEntity user = getUserFromUsername(username);

       return User.builder()
               .username(user.getUsername())
               .password(user.getPassword())
               .authorities(Collections.emptyList())
               .build();
    }
}
