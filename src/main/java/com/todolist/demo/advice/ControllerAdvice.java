package com.todolist.demo.advice;

import com.todolist.demo.exception.TodoItemNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ControllerAdvice {
    @ExceptionHandler({TodoItemNotFoundException.class})
    public ErrorResponse notFoundException(Exception exception) {
        return new ErrorResponse(404, "Not Found");
    }
}
