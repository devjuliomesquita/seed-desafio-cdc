package com.juliomesquita.cdc.book.application.book.crud.mapper;

import com.juliomesquita.cdc.book.application.book.crud.dtos.BookResponse;
import com.juliomesquita.cdc.book.domain.entities.Book;
import com.juliomesquita.cdc.shared.services.GenericMapperRes;
import org.springframework.stereotype.Component;

@Component
public class BookMapperRes implements GenericMapperRes<Book, BookResponse> {

    @Override
    public BookResponse toResponse(final Book entity) {
        return BookResponse.fromResponse(entity);
    }

}
