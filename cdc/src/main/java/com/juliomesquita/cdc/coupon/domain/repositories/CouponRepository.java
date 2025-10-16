package com.juliomesquita.cdc.coupon.domain.repositories;

import com.juliomesquita.cdc.coupon.domain.entities.Coupon;
import com.juliomesquita.cdc.shared.repositories.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends GenericRepository<Coupon> {
}
