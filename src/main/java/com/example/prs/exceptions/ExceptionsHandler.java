package com.example.prs.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ExceptionsHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(GameTerminatedException.class)
    public @ResponseBody ErrorInfo handleGameTerminatedException(GameTerminatedException ex) {
        return new ErrorInfo("Can't play in terminated game");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(GameNotFoundException.class)
    public @ResponseBody ErrorInfo handleGameTerminatedException(GameNotFoundException ex) {
        return new ErrorInfo("Game is not found");
    }
}
