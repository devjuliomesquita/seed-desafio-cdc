package com.juliomesquita.cdc.book.application.category.crud.dtos;


import com.juliomesquita.cdc.book.domain.entities.Category;
import com.juliomesquita.cdc.shared.services.GenericMapperUp;
import com.juliomesquita.cdc.shared.validators.UniqueValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryUpdateRequest(
    @NotBlank(message = "{validation.not.blank}")
    @Size(min = 2, max = 255, message = "{validation.size}")
    @UniqueValue(domainClass = Category.class, fieldName = "name", message = "{validation.unique.value}")
    String name
) implements GenericMapperUp<Category> {
    @Override
    public Category toDomain(Category entity) {
        return entity.update(name);
    }
}
