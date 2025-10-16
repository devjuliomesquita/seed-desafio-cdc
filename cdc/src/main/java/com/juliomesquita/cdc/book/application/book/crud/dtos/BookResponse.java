package com.juliomesquita.cdc.book.application.book.crud.dtos;

import com.juliomesquita.cdc.book.application.author.crud.dtos.AuthorResponse;
import com.juliomesquita.cdc.book.application.category.crud.dtos.CategoryResponse;
import com.juliomesquita.cdc.book.domain.entities.Book;
import com.juliomesquita.cdc.book.domain.valueobjects.BookInfo;
import com.juliomesquita.cdc.book.domain.valueobjects.ISBN;

import java.time.OffsetDateTime;
import java.util.UUID;

public record BookResponse(UUID id, BookInfo info, ISBN isbn, CategoryResponse category, AuthorResponse author, OffsetDateTime createdAt, String summary) {
    public static BookResponse fromResponse(final Book entity) {
        return new BookResponse(entity.getId(), entity.getInfo(), entity.getIsbn(),
            CategoryResponse.fromResponse(entity.getCategory()), AuthorResponse.fromResponse(entity.getAuthor()),
            entity.getCreatedAt(), entity.getSummary());
    }
}
