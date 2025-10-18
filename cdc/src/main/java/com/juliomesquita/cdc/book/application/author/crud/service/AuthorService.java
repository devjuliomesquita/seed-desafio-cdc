package com.juliomesquita.cdc.book.application.author.crud.service;

import com.juliomesquita.cdc.book.application.author.crud.dtos.AuthorCreateRequest;
import com.juliomesquita.cdc.book.application.author.crud.dtos.AuthorResponse;
import com.juliomesquita.cdc.book.application.author.crud.dtos.AuthorUpdateRequest;
import com.juliomesquita.cdc.book.application.author.crud.mapper.AuthorMapperRes;
import com.juliomesquita.cdc.book.domain.entities.Author;
import com.juliomesquita.cdc.book.domain.repositories.AuthorRepository;
import com.juliomesquita.cdc.shared.services.GenericService;
import org.springframework.stereotype.Service;

@Service
public class AuthorService extends GenericService<Author, AuthorCreateRequest, AuthorUpdateRequest, AuthorResponse, AuthorRepository, AuthorMapperRes> {
    public AuthorService(final AuthorRepository repository, final AuthorMapperRes mapper) {
        super(repository, mapper);
    }
}
