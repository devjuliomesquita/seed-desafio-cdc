package com.juliomesquita.cdc.coupon.infrastructure.facade;

import com.juliomesquita.cdc.coupon.application.crud.dtos.CouponResponse;
import org.springframework.modulith.NamedInterface;

@NamedInterface
public interface CouponModuleFacade {
    CouponResponse getCouponByCode(String code);
}
