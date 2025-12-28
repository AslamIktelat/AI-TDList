package com.ai.tdlist.services;

import com.ai.tdlist.entities.UserEntity;
import com.ai.tdlist.exceptions.LoginUserException;
import com.ai.tdlist.exceptions.RegisterUserException;
import com.ai.tdlist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JWTService jwtService;

    public UserEntity registerUser(UserEntity userEntity) {
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            return userRepository.save(userEntity);
        }
        catch (Exception e) {
            throw new RegisterUserException("Registration failed: " + e.getMessage(),e);
        }
    }

    public String login(UserEntity userEntity) {
        try {
            Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userEntity.getUsername(), userEntity.getPassword()));
            if(authentication.isAuthenticated()) {
                return jwtService.generateToken(userEntity.getUsername());
            }

            return "Login failed";
        }
        catch (Exception e) {
            throw new LoginUserException("Login failed: " + e.getMessage(),e);
        }

    }
}
