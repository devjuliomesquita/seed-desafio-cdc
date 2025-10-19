package com.juliomesquita.cdc.book.infrastructure.author.controller;

import com.juliomesquita.cdc.book.application.author.crud.dtos.AuthorCreateRequest;
import com.juliomesquita.cdc.book.application.author.crud.dtos.AuthorResponse;
import com.juliomesquita.cdc.book.application.author.crud.dtos.AuthorUpdateRequest;
import com.juliomesquita.cdc.book.application.author.crud.service.AuthorService;
import com.juliomesquita.cdc.book.domain.entities.Author;
import com.juliomesquita.cdc.book.infrastructure.author.documentation.AuthorDoc;
import com.juliomesquita.cdc.shared.controllers.GenericController;
import com.juliomesquita.cdc.shared.doc.SearchableField;
import com.juliomesquita.cdc.shared.doc.SearchableRelation;
import com.juliomesquita.cdc.shared.repositories.SearchOperation;
import com.juliomesquita.cdc.shared.utils.Filter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.juliomesquita.cdc.shared.repositories.SearchOperation.*;

@RestController
@RequestMapping("/authors")
@Tag(name = "Author", description = "API for management of authors.")
public class AuthorController extends GenericController<Author, AuthorCreateRequest, AuthorUpdateRequest, AuthorResponse, AuthorService> implements AuthorDoc {
    private static final List<String> ALL_OPERATORS = Arrays.asList(SIMPLE_OPERATION_SET);

    public AuthorController(final AuthorService service) {
        super(service);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getSearchMetadataEndpoint() {
        return ResponseEntity.ok(Map.of(
            "searchableFields", getSearchableFields(),
            "searchableRelations", getSearchableRelations()
        ));
    }

    @Override
    public List<SearchableField> getSearchableFields() {
        return List.of(
            new SearchableField("name", "Author name", getStringOperations()),
            new SearchableField("email", "Author email", getStringOperations()),
            new SearchableField("description", "Author description", getStringOperations())
        );
    }

    @Override
    public List<SearchableRelation> getSearchableRelations() {
        return List.of();
    }
}
