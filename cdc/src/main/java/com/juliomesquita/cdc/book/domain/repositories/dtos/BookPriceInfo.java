package com.juliomesquita.cdc.book.domain.repositories.dtos;

import org.springframework.modulith.NamedInterface;

import java.math.BigDecimal;
import java.util.UUID;

@NamedInterface
public record BookPriceInfo(UUID id, BigDecimal price) {
}
