package com.juliomesquita.cdc.livro.application.author.crud.dtos;


import com.juliomesquita.cdc.livro.domain.entities.Author;
import com.juliomesquita.cdc.shared.validators.UniqueValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthorCreateRequest(
    @NotBlank(message = "{validation.not.blank}")
    @Size(min = 2, max = 255, message = "{validation.size}")
    String name,

    @NotBlank(message = "{validation.not.blank}")
    @Email(message = "{validation.invalid.email}")
    @UniqueValue(domainClass = Author.class, fieldName = "email", message = "{validation.unique.value}")
    String email,

    @NotBlank(message = "{validation.not.blank}")
    @Size(max = 400, message = "{validation.size.max}")
    String description
) {
}
