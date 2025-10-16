package com.juliomesquita.cdc.shared.exceptions;

public class InternalApplicationErrorException extends RuntimeException {
    public InternalApplicationErrorException(String message) {
        super(message);
    }
}
