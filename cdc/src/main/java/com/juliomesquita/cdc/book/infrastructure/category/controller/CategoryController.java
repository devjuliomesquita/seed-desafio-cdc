package com.juliomesquita.cdc.book.infrastructure.category.controller;

import com.juliomesquita.cdc.book.application.category.crud.dtos.CategoryCreateRequest;
import com.juliomesquita.cdc.book.application.category.crud.dtos.CategoryResponse;
import com.juliomesquita.cdc.book.application.category.crud.dtos.CategoryUpdateRequest;
import com.juliomesquita.cdc.book.application.category.crud.service.CategoryService;
import com.juliomesquita.cdc.book.domain.entities.Category;
import com.juliomesquita.cdc.shared.controllers.GenericController;
import com.juliomesquita.cdc.shared.utils.Filter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@Tag(name = "Category", description = "API for management of categories.")
public class CategoryController extends GenericController<Category, CategoryCreateRequest, CategoryUpdateRequest, CategoryResponse, Filter, CategoryService> {
    public CategoryController(final CategoryService service) {
        super(service);
    }
}
