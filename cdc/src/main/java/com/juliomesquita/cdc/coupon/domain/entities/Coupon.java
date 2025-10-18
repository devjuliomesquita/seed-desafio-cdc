package com.juliomesquita.cdc.coupon.domain.entities;

import com.juliomesquita.cdc.shared.entities.BaseEntityWithGeneratedId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "coupons")
public class Coupon extends BaseEntityWithGeneratedId {

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "discount_percentage", nullable = false)
    private Double discountPercentage;

    @Column(name = "valid_from", nullable = false)
    private LocalDate validFrom;

    public static Coupon create(String code, Double discountPercentage, LocalDate validFrom) {
        return new Coupon(code, discountPercentage, validFrom);
    }

    public Coupon update(String code, Double discountPercentage, LocalDate validFrom) {
        this.code = code;
        this.discountPercentage = discountPercentage;
        this.validFrom = validFrom;

        return this;
    }

    public Coupon deactivate() {
        this.validFrom = LocalDate.now().minusDays(1);
        return this;
    }


    protected Coupon() {
    }

    private Coupon(String code, Double discountPercentage, LocalDate validFrom) {
        this.code = code;
        this.discountPercentage = discountPercentage;
        this.validFrom = validFrom;
    }

    public String getCode() {
        return code;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }
}
