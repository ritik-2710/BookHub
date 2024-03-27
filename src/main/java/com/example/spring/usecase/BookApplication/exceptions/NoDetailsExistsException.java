package com.example.spring.usecase.BookApplication.exceptions;

public class NoDetailsExistsException extends RuntimeException{

    public NoDetailsExistsException(String message){
        super(message);
    }
}
