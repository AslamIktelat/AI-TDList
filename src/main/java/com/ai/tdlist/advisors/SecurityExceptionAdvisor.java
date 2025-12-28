package com.ai.tdlist.advisors;

import com.ai.tdlist.exceptions.LoginUserException;
import com.ai.tdlist.exceptions.RegisterUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.ai.tdlist.advisors.Helper.buildResponseEntity;

@ControllerAdvice
public class SecurityExceptionAdvisor {
    private static final Logger logger = LoggerFactory.getLogger(SecurityExceptionAdvisor.class);

    @ExceptionHandler(RegisterUserException.class)
    public ResponseEntity<String> handlerRegisterUserException(RegisterUserException registerUserException) {
        logger.error("Register User Exception: {}", registerUserException.getMessage());
        return buildResponseEntity("Register User Exception:", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(LoginUserException.class)
    public ResponseEntity<String> handlerLoginUserException(LoginUserException loginUserException) {
        logger.error("Login User Exception: {}", loginUserException.getMessage());
        return buildResponseEntity("Login User Exception, Try with new TOKEN", HttpStatus.UNAUTHORIZED);
    }
}
