package com.francisco.psjava.gamestore.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Long id) {
        super("Id " + id + " not found");
    }
}
