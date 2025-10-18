package com.juliomesquita.cdc.coupon.infrastructure.facade;

import com.juliomesquita.cdc.coupon.application.crud.dtos.CouponResponse;
import com.juliomesquita.cdc.coupon.domain.entities.Coupon;
import com.juliomesquita.cdc.coupon.domain.repositories.CouponRepository;
import com.juliomesquita.cdc.shared.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
record CouponModuleFacadeImpl(CouponRepository couponRepository) implements CouponModuleFacade {
    @Override
    public CouponResponse getCouponByCode(final String code) {
        if (code == null) {
            throw new IllegalArgumentException("code is null");
        }

        final Coupon coupon = this.couponRepository.findByCode(code)
            .orElseThrow(() -> new ResourceNotFoundException("Coupon not found with code: " + code));

        return CouponResponse.fromResponse(coupon);
    }
}
