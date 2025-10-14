package com.juliomesquita.cdc.livro.application.book.crud.dtos;


import com.juliomesquita.cdc.livro.domain.entities.Author;
import com.juliomesquita.cdc.livro.domain.entities.Book;
import com.juliomesquita.cdc.livro.domain.entities.Category;
import com.juliomesquita.cdc.shared.validators.UniqueValue;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BookRequest(

    @NotBlank(message = "{validation.not.blank}")
    @UniqueValue(domainClass = Book.class, fieldName = "title", message = "{validation.unique.value}")
    String title,

    @NotBlank(message = "{validation.not.blank}")
    @Size(min = 2, max = 500, message = "{validation.size}")
    String abstractText,

    @NotNull(message = "{validation.not.null}")
    String summary,

    @NotNull(message = "{validation.not.null}")
    @DecimalMin(message = "{validation.min}", value = "20.0")
    BigDecimal price,

    @NotNull(message = "{validation.not.null}")
    @Min(value = 100, message = "{validation.min}")
    Integer numberOfPages,

    @NotNull(message = "{validation.not.null}")
    @Future(message = "{validation.future}")
    LocalDate publicationDate,

    @NotBlank(message = "{validation.not.blank}")
    @UniqueValue(domainClass = Book.class, fieldName = "isbn", message = "{validation.unique.value}")
    String isbn,

    @NotNull(message = "{validation.not.null}")
    @UniqueValue(domainClass = Category.class, fieldName = "id", message = "{validation.unique.value}")
    UUID categoryId,

    @NotNull(message = "{validation.not.null}")
    @UniqueValue(domainClass = Author.class, fieldName = "id", message = "{validation.unique.value}")
    UUID authorId
) {
}
