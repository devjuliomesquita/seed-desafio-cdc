package com.juliomesquita.cdc.livro.application.category.crud.mapper;

import com.juliomesquita.cdc.livro.application.category.crud.dtos.CategoryRequest;
import com.juliomesquita.cdc.livro.application.category.crud.dtos.CategoryResponse;
import com.juliomesquita.cdc.livro.domain.entities.Author;
import com.juliomesquita.cdc.livro.domain.entities.Category;
import com.juliomesquita.cdc.shared.services.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements GenericMapper<Category, CategoryRequest, CategoryResponse> {

    @Override
    public Category toEntity(final CategoryRequest request) {
        return Category.create(request.name());
    }

    @Override
    public CategoryResponse toResponse(final Category entity) {
        return CategoryResponse.fromResponse(entity);
    }

    @Override
    public Category updateEntityFromRequest(final CategoryRequest request, final Category entity) {
        return  entity.update(request.name());
    }
}
