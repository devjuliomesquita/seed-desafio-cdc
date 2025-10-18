package com.juliomesquita.cdc.order.application.crud.dtos;

import com.juliomesquita.cdc.order.application.crud.dtos.shared.AddressDto;
import com.juliomesquita.cdc.order.application.crud.dtos.shared.ContactDto;
import com.juliomesquita.cdc.order.application.crud.dtos.shared.GeneralDto;
import com.juliomesquita.cdc.order.application.crud.dtos.shared.OrderItemDto;
import com.juliomesquita.cdc.order.domain.entities.Order;
import com.juliomesquita.cdc.shared.services.GenericMapperUp;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

public record OrderUpdateRequest(
    GeneralDto general,
    AddressDto address,
    ContactDto contact,
    List<OrderItemDto> items,
    String couponCode
) implements GenericMapperUp<Order> {
    @Override
    public Order toDomain(final Order entity) {
        throw new NotImplementedException("Order update not implemented yet");
    }
}
