package com.weg.rocketseatcourse.application.exceptions;

public class UserCantBeDeleted extends RuntimeException {
    public UserCantBeDeleted(String message) {
        super(message);
    }
}
