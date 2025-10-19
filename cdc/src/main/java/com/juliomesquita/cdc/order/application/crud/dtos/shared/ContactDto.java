package com.juliomesquita.cdc.order.application.crud.dtos.shared;

import com.juliomesquita.cdc.order.domain.vo.UserInfoContact;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ContactDto(
    @NotNull(message = "{validation.not.null}")
    @Email(message = "{validation.invalid.email}")
    String email,

    @NotNull(message = "{validation.not.null}")
    @Pattern(
        regexp = "\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}",
        message = "{validation.phone.invalid}"
    )
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
