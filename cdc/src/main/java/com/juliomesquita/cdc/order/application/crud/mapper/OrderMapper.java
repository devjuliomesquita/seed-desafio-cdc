package com.juliomesquita.cdc.order.application.crud.mapper;

import com.juliomesquita.cdc.order.application.crud.dtos.OrderResponse;
import com.juliomesquita.cdc.order.domain.entities.Order;
import com.juliomesquita.cdc.shared.services.GenericMapperRes;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements GenericMapperRes<Order, OrderResponse> {

    @Override
    public OrderResponse toResponse(final Order entity) {
        return OrderResponse.fromResponse(entity);
    }
}
