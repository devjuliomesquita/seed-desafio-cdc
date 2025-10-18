package com.juliomesquita.cdc.book.application.author.crud.dtos;


import com.juliomesquita.cdc.book.domain.entities.Author;
import com.juliomesquita.cdc.shared.services.GenericMapperUp;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthorUpdateRequest(
    @NotBlank(message = "{validation.not.blank}")
    @Size(min = 2, max = 255, message = "{validation.size}")
    String name,

    @NotBlank(message = "{validation.not.blank}")
    @Email(message = "{validation.invalid.email}")
    String email,

    @NotBlank(message = "{validation.not.blank}")
    @Size(max = 400, message = "{validation.size.max}")
    String description
) implements GenericMapperUp<Author> {
    @Override
    public Author toDomain(Author entity) {
        return entity.update(name, email, description);
    }
}
