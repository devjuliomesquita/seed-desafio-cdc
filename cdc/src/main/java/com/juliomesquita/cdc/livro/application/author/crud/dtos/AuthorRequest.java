package com.juliomesquita.cdc.livro.application.author.crud.dtos;


import com.juliomesquita.cdc.livro.domain.entities.Author;
import com.juliomesquita.cdc.shared.validators.UniqueValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthorRequest(
    @NotBlank(message = "O nome não pode estar em branco.")
    @Size(min = 2, max = 255, message = "O nome deve ter entre 2 e 255 caracteres.")
    String name,

    @NotBlank(message = "O email não pode estar em branco.")
    @Email(message = "O formato do email é inválido.")
    @UniqueValue(domainClass = Author.class, fieldName = "email", message = "Este email já está em uso.")
    String email,

    @NotBlank(message = "A descrição não pode estar em branco.")
    @Size(max = 400, message = "A descrição não pode exceder 400 caracteres.")
    String description
) {
}
