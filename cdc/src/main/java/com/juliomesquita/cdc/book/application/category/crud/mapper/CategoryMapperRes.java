package com.juliomesquita.cdc.book.application.category.crud.mapper;

import com.juliomesquita.cdc.book.application.category.crud.dtos.CategoryResponse;
import com.juliomesquita.cdc.book.domain.entities.Category;
import com.juliomesquita.cdc.shared.services.GenericMapperRes;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperRes implements GenericMapperRes<Category, CategoryResponse> {

    @Override
    public CategoryResponse toResponse(final Category entity) {
        return CategoryResponse.fromResponse(entity);
    }

}
