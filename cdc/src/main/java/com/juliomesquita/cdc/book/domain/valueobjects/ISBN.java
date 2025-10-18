package com.juliomesquita.cdc.book.domain.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import org.springframework.util.Assert;

import java.io.Serializable;

@Embeddable
public record ISBN(
    @NotBlank
    String value
) implements Serializable {
    public ISBN {
        // A validação de formato de ISBN (10 ou 13) é complexa e
        // idealmente seria implementada aqui para garantir a integridade do objeto.
        Assert.hasText(value, "O valor do ISBN não pode estar em branco.");
    }

    public static ISBN of(final String value) {
        return new ISBN(value);
    }
}
