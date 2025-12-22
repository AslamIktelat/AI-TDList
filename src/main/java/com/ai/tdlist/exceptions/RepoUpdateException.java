package com.ai.tdlist.exceptions;

import lombok.Getter;

@Getter
public class RepoUpdateException extends RuntimeException {
    private final Exception rootException;
    public RepoUpdateException(String message,Exception rootException) {
        super(message);
        this.rootException=rootException;
    }
}
