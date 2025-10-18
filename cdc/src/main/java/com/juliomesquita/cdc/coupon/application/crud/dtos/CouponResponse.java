package com.juliomesquita.cdc.coupon.application.crud.dtos;

import com.juliomesquita.cdc.coupon.domain.entities.Coupon;
import org.springframework.modulith.NamedInterface;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@NamedInterface
public record CouponResponse(UUID id, String code, Double discountPercentage, LocalDate validFrom, OffsetDateTime createdAt) {
    public static CouponResponse fromResponse(final Coupon entity) {
        return new CouponResponse(
                entity.getId(),
                entity.getCode(),
                entity.getDiscountPercentage(),
                entity.getValidFrom(),
                entity.getCreatedAt()
        );
    }
}
