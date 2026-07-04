package com.example.security.controller;

import com.example.security.domain.entity.AuthRequest;
import com.example.security.domain.entity.UserEntity;
import com.example.security.repository.UserRepository;
import com.example.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwt;

    @GetMapping("/encodePassword")
    public void saveUserWithEncodedPassword(@RequestParam String username, @RequestParam String password){

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setActive(true);

        userRepository.save(user);
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestParam AuthRequest authRequest){
      Authentication authenticate =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                        authRequest.getPassword()));
        if (authenticate.isAuthenticated()){
           String role = authenticate.getAuthorities()
                    .iterator()
                    .next()
                    .getAuthority()
                    .replace("ROLE_","");

            return jwt.generateToken(authRequest.getUsername(), role);
        }

        return null;
    }
}
