package com.harsha.springbootblogapp.exception;

import com.harsha.springbootblogapp.payloads.ApiError;
import com.harsha.springbootblogapp.payloads.ResponseEntityBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalUserExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> details = new ArrayList<String>();
        details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getObjectName() + " : " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError err = new ApiError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Validation Errors",
                details);

        return ResponseEntityBuilder.build(err);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourseNotFound(ResourceNotFoundException ex) {
        

        ApiError err = new ApiError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "No User Id found",
                null);

        return ResponseEntityBuilder.build(err);
    }

}



