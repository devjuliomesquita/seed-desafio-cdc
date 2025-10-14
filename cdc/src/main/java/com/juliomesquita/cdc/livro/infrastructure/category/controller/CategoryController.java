package com.juliomesquita.cdc.livro.infrastructure.category.controller;

import com.juliomesquita.cdc.livro.application.category.crud.dtos.CategoryRequest;
import com.juliomesquita.cdc.livro.application.category.crud.dtos.CategoryResponse;
import com.juliomesquita.cdc.livro.application.category.crud.service.CategoryService;
import com.juliomesquita.cdc.livro.domain.entities.Category;
import com.juliomesquita.cdc.shared.controllers.GenericController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@Tag(name = "Category", description = "API for management of categories.")
public class CategoryController extends GenericController<Category, CategoryRequest, CategoryResponse, CategoryService> {
    public CategoryController(final CategoryService service) {
        super(service);
    }
}
