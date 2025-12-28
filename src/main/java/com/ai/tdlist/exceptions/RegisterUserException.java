package com.ai.tdlist.exceptions;

public class RegisterUserException extends  ControllerException {
    public RegisterUserException(String message,Exception rootException) {
        super(message,rootException);
    }
}
