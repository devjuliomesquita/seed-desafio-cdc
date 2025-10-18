package com.juliomesquita.cdc.order.application.crud.dtos.shared;

import com.juliomesquita.cdc.order.domain.enums.DocumentType;
import com.juliomesquita.cdc.order.domain.vo.UserInfosGeneral;

public record GeneralDto(
    String name,
    String lastName,
    String documentNumber,
    DocumentType documentType
) {
    public static GeneralDto fromDomain(final UserInfosGeneral userInfosGeneral) {
        return new GeneralDto(
            userInfosGeneral.name(),
            userInfosGeneral.lastName(),
            userInfosGeneral.documentNumber(),
            userInfosGeneral.documentType()
        );
    }

    public UserInfosGeneral toDomain() {
        return new UserInfosGeneral(this.name, this.lastName, this.documentNumber, this.documentType);
    }
}
