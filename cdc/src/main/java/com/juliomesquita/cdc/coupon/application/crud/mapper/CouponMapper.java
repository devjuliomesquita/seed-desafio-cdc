package com.juliomesquita.cdc.coupon.application.crud.mapper;

import com.juliomesquita.cdc.coupon.application.crud.dtos.CouponCreateRequest;
import com.juliomesquita.cdc.coupon.application.crud.dtos.CouponResponse;
import com.juliomesquita.cdc.coupon.application.crud.dtos.CouponUpdateRequest;
import com.juliomesquita.cdc.coupon.domain.entities.Coupon;
import com.juliomesquita.cdc.shared.services.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class CouponMapper implements GenericMapper<Coupon, CouponCreateRequest, CouponUpdateRequest, CouponResponse> {
    @Override
    public Coupon toEntity(final CouponCreateRequest request) {
        return Coupon.create(request.code(), request.discountPercentage(), request.validFrom());
    }

    @Override
    public CouponResponse toResponse(final Coupon entity) {
        return CouponResponse.fromResponse(entity);
    }

    @Override
    public Coupon updateEntityFromRequest(final CouponUpdateRequest request, final Coupon entity) {
        return entity.update(request.code(), request.discountPercentage(), request.validFrom());
    }
}
