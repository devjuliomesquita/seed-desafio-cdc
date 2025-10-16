package com.juliomesquita.cdc.book.application.category.crud.mapper;

import com.juliomesquita.cdc.book.application.category.crud.dtos.CategoryCreateRequest;
import com.juliomesquita.cdc.book.application.category.crud.dtos.CategoryResponse;
import com.juliomesquita.cdc.book.application.category.crud.dtos.CategoryUpdateRequest;
import com.juliomesquita.cdc.book.domain.entities.Category;
import com.juliomesquita.cdc.shared.services.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements GenericMapper<Category, CategoryCreateRequest, CategoryUpdateRequest,  CategoryResponse> {

    @Override
    public Category toEntity(final CategoryCreateRequest request) {
        return Category.create(request.name());
    }

    @Override
    public CategoryResponse toResponse(final Category entity) {
        return CategoryResponse.fromResponse(entity);
    }

    @Override
    public Category updateEntityFromRequest(final CategoryUpdateRequest request, final Category entity) {
        return  entity.update(request.name());
    }
}
