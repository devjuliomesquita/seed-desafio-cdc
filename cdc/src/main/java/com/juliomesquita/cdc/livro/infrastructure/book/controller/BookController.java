package com.juliomesquita.cdc.livro.infrastructure.book.controller;

import com.juliomesquita.cdc.livro.application.book.crud.dtos.BookRequest;
import com.juliomesquita.cdc.livro.application.book.crud.dtos.BookResponse;
import com.juliomesquita.cdc.livro.application.book.crud.service.BookService;
import com.juliomesquita.cdc.livro.domain.entities.Book;
import com.juliomesquita.cdc.shared.controllers.GenericController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@Tag(name = "Books", description = "API for management of books.")
public class BookController extends GenericController<Book, BookRequest, BookResponse, BookService> {
    public BookController(final BookService service) {
        super(service);
    }
}
