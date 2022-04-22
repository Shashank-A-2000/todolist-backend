package com.shashank.ToDoList.error;

public class UserIdAlreadyExistsException extends Exception{
    public UserIdAlreadyExistsException() {
        super();
    }

    public UserIdAlreadyExistsException(String message) {
        super(message);
    }

    public UserIdAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIdAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    protected UserIdAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
