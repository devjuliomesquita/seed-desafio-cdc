package com.juliomesquita.cdc.book.application.book.crud.dtos;


import com.juliomesquita.cdc.book.domain.entities.Author;
import com.juliomesquita.cdc.book.domain.entities.Book;
import com.juliomesquita.cdc.book.domain.entities.Category;
import com.juliomesquita.cdc.shared.exceptions.FeatureNotImplementedException;
import com.juliomesquita.cdc.shared.services.GenericMapperCr;
import com.juliomesquita.cdc.shared.validators.ExistValue;
import com.juliomesquita.cdc.shared.validators.UniqueValue;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BookCreateRequest(

    @NotBlank(message = "{validation.not.blank}")
    @UniqueValue(domainClass = Book.class, fieldName = "info.title", message = "{validation.unique.value}")
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
    @UniqueValue(domainClass = Book.class, fieldName = "isbn.value", message = "{validation.unique.value}")
    String isbn,

    @NotNull(message = "{validation.not.null}")
    @ExistValue(domainClass = Category.class, fieldName = "id", message = "{validation.exist.value}")
    UUID categoryId,

    @NotNull(message = "{validation.not.null}")
    @ExistValue(domainClass = Author.class, fieldName = "id", message = "{validation.exist.value}")
    UUID authorId
) implements GenericMapperCr<Book> {
    @Override
    public Book toDomain() {
        throw  new FeatureNotImplementedException("Use BookService.createInstanceBook() instead");
    }
}
