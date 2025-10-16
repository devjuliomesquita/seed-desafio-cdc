package com.juliomesquita.cdc.book.application.author.crud.mapper;

import com.juliomesquita.cdc.book.application.author.crud.dtos.AuthorCreateRequest;
import com.juliomesquita.cdc.book.application.author.crud.dtos.AuthorResponse;
import com.juliomesquita.cdc.book.application.author.crud.dtos.AuthorUpdateRequest;
import com.juliomesquita.cdc.book.domain.entities.Author;
import com.juliomesquita.cdc.shared.services.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper implements GenericMapper<Author, AuthorCreateRequest, AuthorUpdateRequest,  AuthorResponse> {

    @Override
    public Author toEntity(final AuthorCreateRequest request) {
        return Author.create(request.name(), request.email(), request.description());
    }

    @Override
    public AuthorResponse toResponse(final Author entity) {
        return AuthorResponse.fromResponse(entity);
    }

    @Override
    public Author updateEntityFromRequest(final AuthorUpdateRequest request, final Author entity) {
        return  entity.update(request.name(), request.email(), request.description());
    }
}
