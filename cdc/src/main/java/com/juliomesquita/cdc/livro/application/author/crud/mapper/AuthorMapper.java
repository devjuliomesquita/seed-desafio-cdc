package com.juliomesquita.cdc.livro.application.author.crud.mapper;

import com.juliomesquita.cdc.livro.application.author.crud.dtos.AuthorRequest;
import com.juliomesquita.cdc.livro.application.author.crud.dtos.AuthorResponse;
import com.juliomesquita.cdc.livro.domain.entities.Author;
import com.juliomesquita.cdc.shared.services.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper implements GenericMapper<Author, AuthorRequest, AuthorResponse> {

    @Override
    public Author toEntity(final AuthorRequest request) {
        return Author.create(request.name(), request.email(), request.description());
    }

    @Override
    public AuthorResponse toResponse(final Author entity) {
        return AuthorResponse.fromResponse(entity);
    }

    @Override
    public Author updateEntityFromRequest(final AuthorRequest request, final Author entity) {
        return  entity.update(request.name(), request.email(), request.description());
    }
}
