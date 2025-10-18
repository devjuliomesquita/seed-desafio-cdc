package com.juliomesquita.cdc.order.domain.vo;

import jakarta.persistence.Embeddable;

@Embeddable
public record UserInfoContact(
    String email,
    String phoneNumber
) {
}
