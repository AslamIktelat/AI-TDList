package com.ai.tdlist.exceptions;

import lombok.Getter;

@Getter
public class RepoFetchException extends ControllerException {
    public RepoFetchException(String message,Exception rootException) {
        super(message,rootException);
    }
}
