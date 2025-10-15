package com.juliomesquita.cdc.livro.application.category.crud.dtos;

import com.juliomesquita.cdc.livro.domain.entities.Category;

import java.time.OffsetDateTime;
import java.util.UUID;

public record CategoryResponse(UUID id, String name, OffsetDateTime createdAt) {
    public static CategoryResponse fromResponse(final Category entity) {
        return new CategoryResponse(entity.getId(), entity.getName(), entity.getCreatedAt());
    }
}
