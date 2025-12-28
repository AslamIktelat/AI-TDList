package com.ai.tdlist.exceptions;

import lombok.Getter;

@Getter
public class RepoDeleteException extends ControllerException {
    public RepoDeleteException(String message,Exception rootException) {
        super(message, rootException);
    }
}
