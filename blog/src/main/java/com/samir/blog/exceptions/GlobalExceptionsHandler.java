package com.samir.blog.exceptions;

import com.samir.blog.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> ResourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message= ex.getMessage();
        ApiResponse apiResponse=new ApiResponse(message, false);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> MethodArgsNotValidExceptionHandler(MethodArgumentNotValidException ex){
        Map<String, String> response=new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((errors)->{
            String fieldName=((FieldError)errors).getField();
            String message=errors.getDefaultMessage();
            response.put(fieldName, message);
        });

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
