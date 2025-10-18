package com.juliomesquita.cdc.order.domain.vo;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record CouponId(UUID id) {
}
