package com.juliomesquita.cdc.order.domain.vo;

import jakarta.persistence.Embeddable;

@Embeddable
public record UserInfoAddress(
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
}
