package com.juliomesquita.cdc.coupon.application.crud.service;

import com.juliomesquita.cdc.coupon.application.crud.dtos.CouponCreateRequest;
import com.juliomesquita.cdc.coupon.application.crud.dtos.CouponResponse;
import com.juliomesquita.cdc.coupon.application.crud.dtos.CouponUpdateRequest;
import com.juliomesquita.cdc.coupon.application.crud.mapper.CouponMapperRes;
import com.juliomesquita.cdc.coupon.domain.entities.Coupon;
import com.juliomesquita.cdc.coupon.domain.repositories.CouponRepository;
import com.juliomesquita.cdc.shared.services.GenericService;
import org.springframework.stereotype.Service;

@Service
public class CouponService extends GenericService<Coupon, CouponCreateRequest, CouponUpdateRequest, CouponResponse, CouponRepository, CouponMapperRes> {
    public CouponService(final CouponRepository repository, final CouponMapperRes mapper) {
        super(repository, mapper);
    }
}
