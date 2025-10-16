package com.juliomesquita.cdc.livro.application.author.crud.service;

import com.juliomesquita.cdc.livro.application.author.crud.dtos.AuthorCreateRequest;
import com.juliomesquita.cdc.livro.application.author.crud.dtos.AuthorResponse;
import com.juliomesquita.cdc.livro.application.author.crud.dtos.AuthorUpdateRequest;
import com.juliomesquita.cdc.livro.application.author.crud.mapper.AuthorMapper;
import com.juliomesquita.cdc.livro.domain.entities.Author;
import com.juliomesquita.cdc.livro.domain.repositories.AuthorRepository;
import com.juliomesquita.cdc.shared.services.GenericService;
import org.springframework.stereotype.Service;

@Service
public class AuthorService extends GenericService<Author, AuthorCreateRequest, AuthorUpdateRequest, AuthorResponse, AuthorRepository, AuthorMapper> {
    public AuthorService(final AuthorRepository repository, final AuthorMapper mapper) {
        super(repository, mapper);
    }
}
