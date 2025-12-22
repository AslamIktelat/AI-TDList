package com.ai.tdlist.advisors;

import com.ai.tdlist.exceptions.RepoCreateException;
import com.ai.tdlist.exceptions.RepoDeleteException;
import com.ai.tdlist.exceptions.RepoFetchException;
import com.ai.tdlist.exceptions.RepoUpdateException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
        return buildResponseEntity("Repository Creation Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RepoUpdateException.class)
    public ResponseEntity<String> handlerRepoUpdateException(RepoUpdateException repoUpdateException) {
        logger.error("Repository Update Exception: {}", repoUpdateException.getMessage());
        // implement further handling logic for publishing to kafka topic
        if(repoUpdateException.getRootException() instanceof EntityNotFoundException)
        {
            return new ResponseEntity<>("Repository Update Error: Entity Not Found", HttpStatus.NOT_FOUND);
        }
        return buildResponseEntity("Repository Update Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RepoDeleteException.class)
    public ResponseEntity<String> handlerRepoDeleteException(RepoDeleteException repoDeleteException) {
        logger.error("Repository Delete Exception: {}", repoDeleteException.getMessage());
        // implement further handling logic for publishing to kafka topic
        if(repoDeleteException.getRootException() instanceof EntityNotFoundException)
        {
            return new ResponseEntity<>("Repository Delete Error: Entity Not Found", HttpStatus.NOT_FOUND);
        }
        return buildResponseEntity("Repository Delete Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RepoFetchException.class)
    public ResponseEntity<String> handlerRepoFetchException(RepoFetchException repoFetchException) {
        logger.error("Repository Fetch Exception: {}", repoFetchException.getMessage());
        // implement further handling logic for publishing to kafka topic
        if(repoFetchException.getRootException() instanceof EntityNotFoundException)
        {
            return new ResponseEntity<>("Repository Fetch Error: Entity Not Found", HttpStatus.NOT_FOUND);
        }
        return buildResponseEntity("Repository Fetch Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handlerHttpMessageNotReadableException(HttpMessageNotReadableException httpMessageNotReadableException) {
        logger.error("HTTP Message Not Readable Exception: {}", httpMessageNotReadableException.getMessage());
        // implement further handling logic for publishing to kafka topic
        return buildResponseEntity("Malformed JSON Request, "+httpMessageNotReadableException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    //TODO :: Check if this still needed
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handlerEntityNotFoundException(EntityNotFoundException httpMessageNotReadableException) {
        logger.error("Entity Not Found Exception: {}", httpMessageNotReadableException.getMessage());
        // implement further handling logic for publishing to kafka topic
        return new ResponseEntity<>("Entity Not Found, "+httpMessageNotReadableException.getMessage(), HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<String> buildResponseEntity(String message, HttpStatus status) {
        return new ResponseEntity<>(message, status);
    }
}
