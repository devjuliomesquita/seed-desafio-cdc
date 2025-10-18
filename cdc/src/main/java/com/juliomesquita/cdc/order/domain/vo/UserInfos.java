package com.juliomesquita.cdc.order.domain.vo;

import jakarta.persistence.Embeddable;

@Embeddable
public record UserInfos(
    UserInfosGeneral infosGeneral,
    UserInfoAddress address,
    UserInfoContact contact
) {
}
