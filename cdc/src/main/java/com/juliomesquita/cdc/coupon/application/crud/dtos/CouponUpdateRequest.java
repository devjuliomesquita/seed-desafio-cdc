package com.juliomesquita.cdc.coupon.application.crud.dtos;

import com.juliomesquita.cdc.coupon.domain.entities.Coupon;
import com.juliomesquita.cdc.shared.services.GenericMapperUp;

import java.time.LocalDate;

public record CouponUpdateRequest(String code, Double discountPercentage, LocalDate validFrom) implements GenericMapperUp<Coupon> {
    @Override
    public Coupon toDomain(Coupon entity) {
        return entity.update(code, discountPercentage, validFrom);
    }
}
