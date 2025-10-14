package com.juliomesquita.cdc.shared.utils;

import java.util.Set;

public record RestAPIErrorResponse(
    int status,
    String message,
    Set<String> errors
) {
}
