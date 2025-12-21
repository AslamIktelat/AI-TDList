package com.ai.tdlist.advisors;

import com.ai.tdlist.exceptions.RepoCreateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@ControllerAdvice
public class ExceptionAdvisor {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvisor.class);


    @ExceptionHandler(RepoCreateException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handlerRepoCreateException(RepoCreateException repoCreateException) {
        logger.error("Repository Creation Exception: {}", repoCreateException.getMessage());
        // implement further handling logic for publishing to kafka topic
        return new ResponseEntity<>("Repository Creation Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
