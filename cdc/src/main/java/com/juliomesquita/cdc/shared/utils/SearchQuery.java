package com.juliomesquita.cdc.shared.utils;

public record SearchQuery(
    int currentPage,
    int itemsPerPage,
    String terms,
    String sort,
    String direction
) {
}
