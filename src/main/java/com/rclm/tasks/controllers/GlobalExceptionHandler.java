package com.rclm.tasks.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.rclm.tasks.domain.dto.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler({IllegalArgumentException.class})
  public ResponseEntity<ErrorResponse> handleExceptions(RuntimeException e, WebRequest req) {
    ErrorResponse res = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), req.getDescription(false));
    return(new ResponseEntity<>(res, HttpStatus.BAD_REQUEST));
  }
}
