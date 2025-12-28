package com.ai.tdlist.exceptions;

import lombok.Getter;

@Getter
public class ControllerException extends RuntimeException {
    private final Exception rootException;
    public ControllerException(String message, Exception rootException) {
        super(message);
        this.rootException = rootException;
    }
}
