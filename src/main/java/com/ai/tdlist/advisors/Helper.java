package com.ai.tdlist.advisors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Helper {

    public static ResponseEntity<String> buildResponseEntity(String message, HttpStatus status) {
        return new ResponseEntity<>(message, status);
    }
    // Add a funcation to save the catched exception for monitoring purpose
    //Add a function to publish the exception to kafka topic
}
