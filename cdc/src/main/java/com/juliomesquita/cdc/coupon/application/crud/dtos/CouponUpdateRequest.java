package com.juliomesquita.cdc.coupon.application.crud.dtos;

import com.juliomesquita.cdc.coupon.domain.entities.Coupon;
import com.juliomesquita.cdc.shared.services.GenericMapperUp;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CouponUpdateRequest(
    @NotNull(message = "{validation.not.null}")
    String code,

    @DecimalMin(message = "{validation.discount.min}", value = "0.0", inclusive = false)
    Double discountPercentage,

    @Future(message = "{validation.future}")
    LocalDate validFrom
) implements GenericMapperUp<Coupon> {
    @Override
    public Coupon toDomain(Coupon entity) {
        return entity.update(code, discountPercentage, validFrom);
    }
}
