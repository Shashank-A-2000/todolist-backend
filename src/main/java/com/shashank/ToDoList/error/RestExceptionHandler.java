package com.shashank.ToDoList.error;

import com.shashank.ToDoList.entity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@ResponseStatus
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserIdAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> userIdAlreadyExistsException(UserIdAlreadyExistsException user, WebRequest request){
        ErrorMessage msg = new ErrorMessage(HttpStatus.NOT_ACCEPTABLE,user.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
    }

}

