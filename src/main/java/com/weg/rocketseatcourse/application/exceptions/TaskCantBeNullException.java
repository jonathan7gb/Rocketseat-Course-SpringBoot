package com.weg.rocketseatcourse.application.exceptions;

public class TaskCantBeNullException extends RuntimeException {
    public TaskCantBeNullException(String message) {
        super(message);
    }
}
