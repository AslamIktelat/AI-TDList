package com.ai.tdlist.exceptions;

public class LoginUserException extends ControllerException {
    public LoginUserException(String message,Exception rootException) {
        super(message,rootException);
    }
}
