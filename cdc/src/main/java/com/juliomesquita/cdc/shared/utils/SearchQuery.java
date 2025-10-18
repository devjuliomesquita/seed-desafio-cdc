package com.juliomesquita.cdc.shared.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public record SearchQuery(
    int currentPage,
    int itemsPerPage,
    String terms,
    String sort,
    String direction
) {
    public PageRequest toPageRequest() {
        return PageRequest.of(
            this.currentPage(),
            this.itemsPerPage(),
            Sort.by(Sort.Direction.fromString(this.direction()), this.sort())
        );
    }
}
