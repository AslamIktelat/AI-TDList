package com.ai.tdlist.exceptions;

import lombok.Getter;

@Getter
public class RepoUpdateException extends ControllerException {
    public RepoUpdateException(String message,Exception rootException) {
        super(message,rootException);
    }
}
