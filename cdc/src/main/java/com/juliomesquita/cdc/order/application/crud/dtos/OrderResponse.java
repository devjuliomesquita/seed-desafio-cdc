package com.juliomesquita.cdc.order.application.crud.dtos;

import com.juliomesquita.cdc.order.application.crud.dtos.shared.AddressDto;
import com.juliomesquita.cdc.order.application.crud.dtos.shared.ContactDto;
import com.juliomesquita.cdc.order.application.crud.dtos.shared.GeneralDto;
import com.juliomesquita.cdc.order.application.crud.dtos.shared.OrderItemDto;
import com.juliomesquita.cdc.order.domain.entities.Order;
import com.juliomesquita.cdc.order.domain.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record OrderResponse(
    UUID orderId,
    GeneralDto general,
    AddressDto address,
    ContactDto contact,
    List<OrderItemDto> items,
    BigDecimal totalPrice,
    UUID couponId,
    BigDecimal discountPrice,
    OrderStatus status
) {
    public static OrderResponse fromResponse(final Order entity) {
        final GeneralDto general = GeneralDto.fromDomain(entity.getUserInfos().infosGeneral());
        final AddressDto address = AddressDto.fromDomain(entity.getUserInfos().address());
        final ContactDto contact = ContactDto.fromDomain(entity.getUserInfos().contact());
        final List<OrderItemDto> items = entity.getItems().entrySet().stream()
            .map(item -> new OrderItemDto(item.getKey().id(), item.getValue()))
            .toList();
        final UUID couponId = entity.getCouponId() != null ? entity.getCouponId().id() : null;

        return new OrderResponse(
            entity.getId(),
            general,
            address,
            contact,
            items,
            entity.getTotalPrice(),
            couponId,
            entity.getDiscountPrice(),
            entity.getStatus()
        );
    }
}
