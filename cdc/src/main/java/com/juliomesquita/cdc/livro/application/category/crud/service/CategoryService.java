package com.juliomesquita.cdc.livro.application.category.crud.service;

import com.juliomesquita.cdc.livro.application.category.crud.dtos.CategoryRequest;
import com.juliomesquita.cdc.livro.application.category.crud.dtos.CategoryResponse;
import com.juliomesquita.cdc.livro.application.category.crud.mapper.CategoryMapper;
import com.juliomesquita.cdc.livro.domain.entities.Author;
import com.juliomesquita.cdc.livro.domain.entities.Category;
import com.juliomesquita.cdc.livro.domain.repositories.AuthorRepository;
import com.juliomesquita.cdc.livro.domain.repositories.CategoryRepository;
import com.juliomesquita.cdc.shared.services.GenericService;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends GenericService<Category, CategoryRequest, CategoryResponse, CategoryRepository, CategoryMapper> {
    public CategoryService(final CategoryRepository repository, final CategoryMapper mapper) {
        super(repository, mapper);
    }
}
