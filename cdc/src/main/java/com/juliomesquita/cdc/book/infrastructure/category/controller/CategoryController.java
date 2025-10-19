package com.juliomesquita.cdc.book.infrastructure.category.controller;

import com.juliomesquita.cdc.book.application.category.crud.dtos.CategoryCreateRequest;
import com.juliomesquita.cdc.book.application.category.crud.dtos.CategoryResponse;
import com.juliomesquita.cdc.book.application.category.crud.dtos.CategoryUpdateRequest;
import com.juliomesquita.cdc.book.application.category.crud.service.CategoryService;
import com.juliomesquita.cdc.book.domain.entities.Category;
import com.juliomesquita.cdc.book.infrastructure.category.documentation.CategoryDoc;
import com.juliomesquita.cdc.shared.controllers.GenericController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.juliomesquita.cdc.book.infrastructure.category.documentation.CategoryMetadata.searchableFields;
import static com.juliomesquita.cdc.book.infrastructure.category.documentation.CategoryMetadata.searchableRelations;

@RestController
@RequestMapping("/categories")
@Tag(name = "Category", description = "API for management of categories.")
public class CategoryController extends GenericController<Category, CategoryCreateRequest, CategoryUpdateRequest, CategoryResponse, CategoryService> implements CategoryDoc {
    public CategoryController(final CategoryService service) {
        super(service);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getSearchMetadataEndpoint() {
        return ResponseEntity.ok(Map.of(
            "searchableFields", searchableFields(),
            "searchableRelations", searchableRelations()
        ));
    }
}
