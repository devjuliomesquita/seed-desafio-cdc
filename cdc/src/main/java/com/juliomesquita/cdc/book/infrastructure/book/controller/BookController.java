package com.juliomesquita.cdc.book.infrastructure.book.controller;

import com.juliomesquita.cdc.book.application.book.crud.dtos.BookCreateRequest;
import com.juliomesquita.cdc.book.application.book.crud.dtos.BookResponse;
import com.juliomesquita.cdc.book.application.book.crud.dtos.BookUpdateRequest;
import com.juliomesquita.cdc.book.application.book.crud.service.BookService;
import com.juliomesquita.cdc.book.application.book.usecases.findpartial.BookPartialResponse;
import com.juliomesquita.cdc.book.application.book.usecases.findpartial.FindPartialUseCase;
import com.juliomesquita.cdc.book.domain.entities.Book;
import com.juliomesquita.cdc.book.infrastructure.book.documentation.BookDoc;
import com.juliomesquita.cdc.shared.controllers.GenericController;
import com.juliomesquita.cdc.shared.utils.Filter;
import com.juliomesquita.cdc.shared.utils.Pagination;
import com.juliomesquita.cdc.shared.utils.SearchQuery;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.juliomesquita.cdc.book.infrastructure.book.documentation.BookMetadata.searchableFields;
import static com.juliomesquita.cdc.book.infrastructure.book.documentation.BookMetadata.searchableRelations;

@RestController
@RequestMapping("/books")
@Tag(name = "Books", description = "API for management of books.")
public class BookController extends GenericController<Book, BookCreateRequest, BookUpdateRequest, BookResponse, BookService>
    implements BookDoc {
    private final FindPartialUseCase findPartialUseCase;

    public BookController(final BookService service, final FindPartialUseCase findPartialUseCase) {
        super(service);
        this.findPartialUseCase = Objects.requireNonNull(findPartialUseCase, "findPartialUseCase");
    }

    @Override
    public ResponseEntity<Pagination<BookPartialResponse>> findAllPartial(int page, int size,  String sort, String direction, List<Filter> filters) {
        final SearchQuery searchQuery = new SearchQuery(page, size, sort, direction, filters);
        final Pagination<BookPartialResponse> response = this.findPartialUseCase.execute(searchQuery);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getSearchMetadataEndpoint() {
        return ResponseEntity.ok(Map.of(
            "searchableFields", searchableFields(),
            "searchableRelations", searchableRelations()
        ));
    }
}
