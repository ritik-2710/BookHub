package com.example.spring.usecase.BookApplication.exceptionhandling;

import com.example.spring.usecase.BookApplication.errors.ErrorResponse;
import com.example.spring.usecase.BookApplication.exceptions.AlreadyExistsException;
import com.example.spring.usecase.BookApplication.exceptions.NoDetailsExistsException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static com.example.spring.usecase.BookApplication.constants.BookConstants.BAD_REQUEST;
import static com.example.spring.usecase.BookApplication.constants.BookConstants.VALIDATION_FAILED;

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex) {

        List<String> errorDetails = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            errorDetails.add(error.getDefaultMessage());
        }

        ErrorResponse errorResponse = new ErrorResponse(VALIDATION_FAILED, errorDetails);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoDetailsExistsException.class)
    protected ResponseEntity<ErrorResponse> handleNoDetailsExistsException(
            NoDetailsExistsException ex) {
        List<String> errorDetails = new ArrayList<>();
        errorDetails.add(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST, errorDetails);
         return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    protected ResponseEntity<ErrorResponse> handleAlreadyExistsException(
            AlreadyExistsException ex) {
        List<String> errorDetails = new ArrayList<>();
        errorDetails.add(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST, errorDetails);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<ErrorResponse> handleValidationException(
            ValidationException ex) {
        List<String> errorDetails = new ArrayList<>();
        errorDetails.add(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST, errorDetails);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
