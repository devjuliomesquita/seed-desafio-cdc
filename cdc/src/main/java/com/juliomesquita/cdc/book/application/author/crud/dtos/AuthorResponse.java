package com.juliomesquita.cdc.book.application.author.crud.dtos;

import com.juliomesquita.cdc.book.domain.entities.Author;

import java.time.OffsetDateTime;
import java.util.UUID;

public record AuthorResponse(UUID id, String name, String email, String description, OffsetDateTime createdAt) {
    public static AuthorResponse fromResponse(final Author entity) {
        return new AuthorResponse(entity.getId(), entity.getName(), entity.getEmail(), entity.getDescription(), entity.getCreatedAt());
    }
}
