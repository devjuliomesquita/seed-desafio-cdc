package com.juliomesquita.cdc.order.domain.vo;

import com.juliomesquita.cdc.order.domain.enums.DocumentType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public record UserInfosGeneral(
    String name,
    String lastName,
    String documentNumber,
    @Enumerated(EnumType.STRING)
    DocumentType documentType
) {
}
