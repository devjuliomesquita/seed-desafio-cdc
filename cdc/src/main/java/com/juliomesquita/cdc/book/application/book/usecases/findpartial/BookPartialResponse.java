package com.juliomesquita.cdc.book.application.book.usecases.findpartial;

import com.juliomesquita.cdc.book.domain.entities.Book;

import java.util.UUID;

public record BookPartialResponse(UUID id, String title) {
    public static BookPartialResponse from(final Book book) {
        return new BookPartialResponse(book.getId(), book.getInfo().title());
    }
}
