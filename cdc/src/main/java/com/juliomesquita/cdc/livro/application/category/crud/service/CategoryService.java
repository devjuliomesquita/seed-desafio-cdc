package com.juliomesquita.cdc.livro.application.category.crud.service;

import com.juliomesquita.cdc.livro.application.category.crud.dtos.CategoryCreateRequest;
import com.juliomesquita.cdc.livro.application.category.crud.dtos.CategoryResponse;
import com.juliomesquita.cdc.livro.application.category.crud.dtos.CategoryUpdateRequest;
import com.juliomesquita.cdc.livro.application.category.crud.mapper.CategoryMapper;
import com.juliomesquita.cdc.livro.domain.entities.Category;
import com.juliomesquita.cdc.livro.domain.repositories.CategoryRepository;
import com.juliomesquita.cdc.shared.services.GenericService;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends GenericService<Category, CategoryCreateRequest, CategoryUpdateRequest,  CategoryResponse, CategoryRepository, CategoryMapper> {
    public CategoryService(final CategoryRepository repository, final CategoryMapper mapper) {
        super(repository, mapper);
    }
}
