package com.juliomesquita.cdc.book.application.author.crud.mapper;

import com.juliomesquita.cdc.book.application.author.crud.dtos.AuthorResponse;
import com.juliomesquita.cdc.book.domain.entities.Author;
import com.juliomesquita.cdc.shared.services.GenericMapperRes;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapperRes implements GenericMapperRes<Author, AuthorResponse> {

    @Override
    public AuthorResponse toResponse(final Author entity) {
        return AuthorResponse.fromResponse(entity);
    }

}
