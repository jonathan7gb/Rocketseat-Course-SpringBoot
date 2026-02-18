package com.weg.rocketseatcourse.application.exceptions;

public class UserCantBeNullException extends RuntimeException {
    public UserCantBeNullException(String message) {
        super(message);
    }
}
