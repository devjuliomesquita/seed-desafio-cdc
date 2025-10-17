package com.juliomesquita.cdc.coupon.application.crud.dtos;

import com.juliomesquita.cdc.coupon.domain.entities.Coupon;
import com.juliomesquita.cdc.shared.services.GenericMapperCr;

import java.time.LocalDate;

public record CouponCreateRequest(String code, Double discountPercentage, LocalDate validFrom) implements GenericMapperCr<Coupon> {
    @Override
    public Coupon toDomain() {
        return Coupon.create(code, discountPercentage, validFrom);
    }
}
