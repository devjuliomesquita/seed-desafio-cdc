package com.juliomesquita.cdc.order.application.crud.dtos.shared;

import java.util.UUID;

public record OrderItemDto(
    UUID bookId,
    Integer quantity
) {
}
