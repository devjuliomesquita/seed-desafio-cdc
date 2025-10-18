package com.juliomesquita.cdc.coupon.domain.repositories;

import com.juliomesquita.cdc.coupon.domain.entities.Coupon;
import com.juliomesquita.cdc.shared.repositories.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponRepository extends GenericRepository<Coupon> {
    Optional<Coupon> findByCode(String code);
}
