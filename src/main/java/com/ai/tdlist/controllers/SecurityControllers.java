package com.ai.tdlist.controllers;

import com.ai.tdlist.entities.UserEntity;
import com.ai.tdlist.repository.UserRepository;
import com.ai.tdlist.services.JWTService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
public class SecurityControllers {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JWTService jwtService;

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
     return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/register")
    public UserEntity registerUser(@RequestBody UserEntity userEntity) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }
    @GetMapping("/login")
    public String login(@RequestBody UserEntity userEntity) {
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userEntity.getUsername(), userEntity.getPassword()));
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(userEntity.getUsername());
        }

        return "Login failed";
    }
}
