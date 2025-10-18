package com.juliomesquita.cdc.order.application.crud.dtos.shared;

import com.juliomesquita.cdc.order.domain.vo.UserInfoContact;

public record ContactDto(
    String email,
    String phoneNumber
) {
    public static ContactDto fromDomain(final UserInfoContact contact) {
        return new ContactDto(
            contact.email(),
            contact.phoneNumber()
        );
    }

    public UserInfoContact toDomain(){
        return new UserInfoContact(this.email, this.phoneNumber);
    }
}
