package com.juliomesquita.cdc.livro.application.category.crud.dtos;


import com.juliomesquita.cdc.livro.domain.entities.Category;
import com.juliomesquita.cdc.shared.validators.UniqueValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequest(
    @NotBlank(message = "{validation.not.blank}")
    @Size(min = 2, max = 255, message = "{validation.size}")
    @UniqueValue(domainClass = Category.class, fieldName = "name", message = "{validation.unique.value}")
    String name
) {
}
