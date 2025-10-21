package com.juliomesquita.cdc.shared.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.fromString;

public record SearchQuery(
    int currentPage,
    int itemsPerPage,
    String sort,
    String direction,
    List<Filter> filters
) {
    public PageRequest toPageRequest() {
        return PageRequest.of(
            this.currentPage(),
            this.itemsPerPage(),
            Sort.by(fromString(
                this.direction() != null ? this.direction() : "asc"),
                this.sort() != null ? this.sort() : "id")
        );
    }
}
