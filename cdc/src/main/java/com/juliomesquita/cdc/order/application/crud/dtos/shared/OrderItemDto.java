package com.juliomesquita.cdc.order.application.crud.dtos.shared;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record OrderItemDto(
    @NotNull(message = "{validation.not.null}")
    UUID bookId,

    @NotNull(message = "{validation.not.null}")
    @Min(message = "{validation.min.value}", value = 1)
    Integer quantity
) {
}
