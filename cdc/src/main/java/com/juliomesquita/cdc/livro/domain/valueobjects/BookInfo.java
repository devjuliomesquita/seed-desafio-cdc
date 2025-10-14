package com.juliomesquita.cdc.livro.domain.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Embeddable
public record BookInfo(
    @NotBlank
    String title,

    @NotBlank @Size(max = 500)
    String abstractText,

    @NotNull
    String summary,

    @NotNull @DecimalMin(value = "20.0", inclusive = true)
    BigDecimal price,

    @NotNull @Min(100)
    Integer numberOfPages,

    @NotNull
    LocalDate publicationDate
) implements Serializable {
    public BookInfo {
        Assert.isTrue(price.compareTo(new BigDecimal("20.0")) >= 0, "O preço deve ser de no mínimo 20.0.");
        Assert.isTrue(numberOfPages >= 100, "O livro deve ter no mínimo 100 páginas.");
    }

    public static BookInfo of(
        final String title,
        final String abstractText,
        final String summary,
        final BigDecimal price,
        final Integer numberOfPages,
        final LocalDate publicationDate
    ) {
        return new BookInfo(title, abstractText, summary, price, numberOfPages, publicationDate);
    }
}
