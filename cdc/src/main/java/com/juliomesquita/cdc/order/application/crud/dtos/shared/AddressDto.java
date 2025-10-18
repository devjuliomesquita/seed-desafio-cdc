package com.juliomesquita.cdc.order.application.crud.dtos.shared;

import com.juliomesquita.cdc.order.domain.vo.UserInfoAddress;

public record AddressDto(
    String cep,
    String street,
    String number,
    String complement,
    String neighborhood,
    String city,
    String region,
    String uf,
    String state,
    String country
) {
    public static AddressDto fromDomain(final UserInfoAddress address) {
        return new AddressDto(
            address.cep(),
            address.street(),
            address.number(),
            address.complement(),
            address.neighborhood(),
            address.city(),
            address.region(),
            address.uf(),
            address.state(),
            address.country()
        );
    }

    public UserInfoAddress toDomain(){
        return new UserInfoAddress(
            this.cep, this.street, this.number, this.complement, this.neighborhood, this.city, this.region, this.uf, state, this.country);
    }
}
