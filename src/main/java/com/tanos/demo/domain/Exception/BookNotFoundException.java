package com.tanos.demo.domain.Exception;

public class BookNotFoundException extends Exception{

    public BookNotFoundException(String message) {
        super(message);
    }
}
