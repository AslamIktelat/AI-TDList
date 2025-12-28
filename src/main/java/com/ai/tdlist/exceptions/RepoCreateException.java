package com.ai.tdlist.exceptions;


import lombok.Getter;

@Getter
public class RepoCreateException extends ControllerException {

    public RepoCreateException(String message,Exception rootException) {
        super(message, rootException);
    }
}
