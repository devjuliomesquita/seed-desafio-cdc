package com.juliomesquita.cdc.livro.infrastructure.author.controller;

import com.juliomesquita.cdc.livro.application.author.crud.dtos.AuthorCreateRequest;
import com.juliomesquita.cdc.livro.application.author.crud.dtos.AuthorResponse;
import com.juliomesquita.cdc.livro.application.author.crud.dtos.AuthorUpdateRequest;
import com.juliomesquita.cdc.livro.application.author.crud.service.AuthorService;
import com.juliomesquita.cdc.livro.domain.entities.Author;
import com.juliomesquita.cdc.livro.infrastructure.author.documentation.AuthorDoc;
import com.juliomesquita.cdc.shared.controllers.GenericController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
@Tag(name = "Author", description = "API for management of authors.")
public class AuthorController extends GenericController<Author, AuthorCreateRequest, AuthorUpdateRequest, AuthorResponse, AuthorService> implements AuthorDoc {
    public AuthorController(final AuthorService service) {
        super(service);
    }

}
