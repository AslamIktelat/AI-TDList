package com.ai.tdlist.exceptions;

import lombok.Getter;

@Getter
public class RepoDeleteException extends RuntimeException {
    private final Exception rootException;
    public RepoDeleteException(String message,Exception rootException) {
        super(message);
        this.rootException=rootException;
    }
}
