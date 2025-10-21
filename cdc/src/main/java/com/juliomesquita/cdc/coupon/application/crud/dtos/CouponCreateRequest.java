package com.juliomesquita.cdc.coupon.application.crud.dtos;

import com.juliomesquita.cdc.coupon.domain.entities.Coupon;
import com.juliomesquita.cdc.shared.services.GenericMapperCr;
import com.juliomesquita.cdc.shared.validators.UniqueValue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CouponCreateRequest(
    @NotNull(message = "{validation.not.null}")
    @UniqueValue(message = "{validation.unique.value}", domainClass = Coupon.class, fieldName = "code")
    String code,

    @DecimalMin(message = "{validation.discount.min}", value = "0.0", inclusive = false)
    Double discountPercentage,

    @Future(message = "{validation.future}")
    LocalDate validFrom
) implements GenericMapperCr<Coupon> {
    @Override
    public Coupon toDomain() {
        return Coupon.create(code, discountPercentage, validFrom);
    }
}
