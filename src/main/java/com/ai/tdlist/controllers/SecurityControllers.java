package com.ai.tdlist.controllers;

import com.ai.tdlist.entities.UserEntity;
import com.ai.tdlist.services.SecurityService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
public class SecurityControllers {
    @Autowired
    SecurityService securityService;

    @GetMapping("/csrf-token")
    public ResponseEntity<CsrfToken> getCsrfToken(HttpServletRequest request) {
        //** No token will be returned since it's toggled off in config **//
     return new ResponseEntity<>((CsrfToken) request.getAttribute("_csrf"), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@RequestBody UserEntity userEntity) {
       return new ResponseEntity<>(securityService.registerUser(userEntity), HttpStatus.CREATED);
    }
    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserEntity userEntity) {
        return new ResponseEntity<>(securityService.login(userEntity), HttpStatus.OK);
    }
}
