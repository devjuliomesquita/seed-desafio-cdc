package com.juliomesquita.cdc.coupon.application.crud.mapper;

import com.juliomesquita.cdc.coupon.application.crud.dtos.CouponResponse;
import com.juliomesquita.cdc.coupon.domain.entities.Coupon;
import com.juliomesquita.cdc.shared.services.GenericMapperRes;
import org.springframework.stereotype.Component;

@Component
public class CouponMapperRes implements GenericMapperRes<Coupon, CouponResponse> {

    @Override
    public CouponResponse toResponse(final Coupon entity) {
        return CouponResponse.fromResponse(entity);
    }

}
