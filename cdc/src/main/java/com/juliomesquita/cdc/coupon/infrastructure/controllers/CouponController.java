package com.juliomesquita.cdc.coupon.infrastructure.controllers;

import com.juliomesquita.cdc.coupon.application.crud.dtos.CouponCreateRequest;
import com.juliomesquita.cdc.coupon.application.crud.dtos.CouponResponse;
import com.juliomesquita.cdc.coupon.application.crud.dtos.CouponUpdateRequest;
import com.juliomesquita.cdc.coupon.application.crud.service.CouponService;
import com.juliomesquita.cdc.coupon.domain.entities.Coupon;
import com.juliomesquita.cdc.coupon.infrastructure.documentation.CouponDoc;
import com.juliomesquita.cdc.shared.controllers.GenericController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.juliomesquita.cdc.coupon.infrastructure.documentation.CouponMetadata.searchableFields;
import static com.juliomesquita.cdc.coupon.infrastructure.documentation.CouponMetadata.searchableRelations;


@RestController
@RequestMapping("/coupons")
@Tag(name = "Coupons", description = "API for management of Coupons.")
public class CouponController extends GenericController<Coupon, CouponCreateRequest, CouponUpdateRequest, CouponResponse, CouponService> implements CouponDoc {
    public CouponController(final CouponService service) {
        super(service);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getSearchMetadataEndpoint() {
        return ResponseEntity.ok(Map.of(
            "searchableFields", searchableFields(),
            "searchableRelations", searchableRelations()
        ));
    }
}
