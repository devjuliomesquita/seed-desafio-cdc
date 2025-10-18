package com.juliomesquita.cdc.order.application.crud.dtos;

import com.juliomesquita.cdc.order.application.crud.dtos.shared.AddressDto;
import com.juliomesquita.cdc.order.application.crud.dtos.shared.ContactDto;
import com.juliomesquita.cdc.order.application.crud.dtos.shared.GeneralDto;
import com.juliomesquita.cdc.order.domain.entities.Order;
import com.juliomesquita.cdc.order.domain.vo.UserInfoAddress;
import com.juliomesquita.cdc.order.domain.vo.UserInfoContact;
import com.juliomesquita.cdc.order.domain.vo.UserInfos;
import com.juliomesquita.cdc.order.domain.vo.UserInfosGeneral;
import com.juliomesquita.cdc.shared.services.GenericMapperCr;

public record OrderCreateRequest(
    GeneralDto general,
    AddressDto address,
    ContactDto contact

) implements GenericMapperCr<Order> {
    @Override
    public Order toDomain() {
        final UserInfoAddress address = this.address.toDomain();
        final UserInfoContact contact = this.contact.toDomain();
        final UserInfosGeneral general = this.general.toDomain();
        final UserInfos userInfos = new UserInfos(general, address, contact);
        return Order.createOrder(userInfos);
    }
}

