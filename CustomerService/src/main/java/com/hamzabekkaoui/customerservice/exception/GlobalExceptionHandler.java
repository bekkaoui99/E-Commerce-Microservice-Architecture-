package com.hamzabekkaoui.customerservice.exception;

import com.hamzabekkaoui.customerservice.dto.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ExceptionResponse resourceNotFoundException(ResourceNotFoundException ex){
        return ExceptionResponse.builder()
                .message(ex.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }


    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ExceptionResponse resourceAlreadyExistException(ResourceAlreadyExistException ex){
        return ExceptionResponse.builder()
                .message(ex.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ExceptionResponse global(RuntimeException ex){
        return ExceptionResponse.builder()
                .message(ex.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

}
