package com.juliomesquita.cdc.shared.utils;

public record Filter(
    String field,
    String value,
    String operator,
    String relation
) {
}
