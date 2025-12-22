package com.ai.tdlist.exceptions;

import lombok.Getter;

@Getter
public class RepoFetchException extends RuntimeException {
    private final Exception rootException;
    public RepoFetchException(String message,Exception rootException) {
        super(message);
        this.rootException=rootException;
    }
}
