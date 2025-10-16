package com.juliomesquita.cdc.book.application.book.crud.mapper;

import com.juliomesquita.cdc.book.application.book.crud.dtos.BookCreateRequest;
import com.juliomesquita.cdc.book.application.book.crud.dtos.BookResponse;
import com.juliomesquita.cdc.book.application.book.crud.dtos.BookUpdateRequest;
import com.juliomesquita.cdc.book.domain.entities.Book;
import com.juliomesquita.cdc.shared.exceptions.FeatureNotImplementedException;
import com.juliomesquita.cdc.shared.services.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements GenericMapper<Book, BookCreateRequest, BookUpdateRequest, BookResponse> {

    @Override
    public Book toEntity(final BookCreateRequest request) {
        throw  new FeatureNotImplementedException("Not implemented yet.");
    }

    @Override
    public BookResponse toResponse(final Book entity) {
        return BookResponse.fromResponse(entity);
    }

    @Override
    public Book updateEntityFromRequest(final BookUpdateRequest request, final Book entity) {
        throw  new FeatureNotImplementedException("Not implemented yet.");
    }
}
