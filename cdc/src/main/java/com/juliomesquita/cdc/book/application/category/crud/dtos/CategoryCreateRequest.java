package com.juliomesquita.cdc.book.application.category.crud.dtos;


import com.juliomesquita.cdc.book.domain.entities.Category;
import com.juliomesquita.cdc.shared.services.GenericMapperCr;
import com.juliomesquita.cdc.shared.validators.UniqueValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryCreateRequest(
    @NotBlank(message = "{validation.not.blank}")
    @Size(min = 2, max = 255, message = "{validation.size}")
    @UniqueValue(domainClass = Category.class, fieldName = "name", message = "{validation.unique.value}")
    String name
) implements GenericMapperCr<Category> {
    @Override
    public Category toDomain() {
        return Category.create(name);
    }
}
