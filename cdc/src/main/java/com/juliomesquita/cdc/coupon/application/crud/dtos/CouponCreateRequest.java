package com.juliomesquita.cdc.coupon.application.crud.dtos;

import java.time.LocalDate;

public record CouponCreateRequest(String code, Double discountPercentage, LocalDate validFrom) {
}
