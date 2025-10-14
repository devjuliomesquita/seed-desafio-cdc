package com.juliomesquita.cdc.livro.infrastructure.book.controller;

import com.juliomesquita.cdc.livro.application.book.crud.dtos.BookRequest;
import com.juliomesquita.cdc.livro.application.book.crud.dtos.BookResponse;
import com.juliomesquita.cdc.livro.application.book.crud.service.BookService;
import com.juliomesquita.cdc.livro.application.book.usecases.findpartial.BookPartialResponse;
import com.juliomesquita.cdc.livro.application.book.usecases.findpartial.FindPartialUseCase;
import com.juliomesquita.cdc.livro.domain.entities.Book;
import com.juliomesquita.cdc.livro.infrastructure.book.documentation.BookDoc;
import com.juliomesquita.cdc.shared.controllers.GenericController;
import com.juliomesquita.cdc.shared.utils.Pagination;
import com.juliomesquita.cdc.shared.utils.SearchQuery;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/books")
@Tag(name = "Books", description = "API for management of books.")
public class BookController extends GenericController<Book, BookRequest, BookResponse, BookService>
implements BookDoc {
    private final FindPartialUseCase findPartialUseCase;
    public BookController(final BookService service, final FindPartialUseCase findPartialUseCase) {
        super(service);
        this.findPartialUseCase = Objects.requireNonNull(findPartialUseCase, "findPartialUseCase");
    }

    @Override
    public ResponseEntity<Pagination<BookPartialResponse>> findAllPartial(int page, int size, String terms, String sort, String direction) {
        final SearchQuery searchQuery = new SearchQuery(page, size, terms, sort, direction);
        final Pagination<BookPartialResponse> response = this.findPartialUseCase.execute(searchQuery);
        return ResponseEntity.ok(response);
    }
}
