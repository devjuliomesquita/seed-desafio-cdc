package com.juliomesquita.cdc.livro.application.book.crud.mapper;

import com.juliomesquita.cdc.livro.application.book.crud.dtos.BookRequest;
import com.juliomesquita.cdc.livro.application.book.crud.dtos.BookResponse;
import com.juliomesquita.cdc.livro.domain.entities.Author;
import com.juliomesquita.cdc.livro.domain.entities.Book;
import com.juliomesquita.cdc.livro.domain.valueobjects.BookInfo;
import com.juliomesquita.cdc.livro.domain.valueobjects.ISBN;
import com.juliomesquita.cdc.shared.exceptions.FeatureNotImplementedException;
import com.juliomesquita.cdc.shared.services.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements GenericMapper<Book, BookRequest, BookResponse> {

    @Override
    public Book toEntity(final BookRequest request) {
        throw  new FeatureNotImplementedException("Not implemented yet.");
    }

    @Override
    public BookResponse toResponse(final Book entity) {
        return BookResponse.fromResponse(entity);
    }

    @Override
    public Book updateEntityFromRequest(final BookRequest request, final Book entity) {
        throw  new FeatureNotImplementedException("Not implemented yet.");
    }
}
