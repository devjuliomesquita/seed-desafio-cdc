package com.juliomesquita.cdc.shared.exceptions;

public class FieldDuplicatedException extends RuntimeException {
    public FieldDuplicatedException(String message) {
        super(message);
    }
}
