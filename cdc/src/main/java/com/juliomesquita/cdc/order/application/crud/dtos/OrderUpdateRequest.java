package com.juliomesquita.cdc.order.application.crud.dtos;

import com.juliomesquita.cdc.order.application.crud.dtos.shared.AddressDto;
import com.juliomesquita.cdc.order.application.crud.dtos.shared.ContactDto;
import com.juliomesquita.cdc.order.application.crud.dtos.shared.GeneralDto;
import com.juliomesquita.cdc.order.application.crud.dtos.shared.OrderItemDto;
import com.juliomesquita.cdc.order.domain.entities.Order;
import com.juliomesquita.cdc.shared.services.GenericMapperUp;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

public record OrderUpdateRequest(
    @NotNull(message = "{validation.not.null}")
    GeneralDto general,

    @NotNull(message = "{validation.not.null}")
    AddressDto address,

    @NotNull(message = "{validation.not.null}")
    ContactDto contact,

    @NotNull(message = "{validation.not.null}")
    @Size(min = 1, message = "{validation.list.empty}")
    List<OrderItemDto> items,

    String couponCode
) implements GenericMapperUp<Order> {
    @Override
    public Order toDomain(final Order entity) {
        throw new NotImplementedException("Order update not implemented yet");
    }
}
