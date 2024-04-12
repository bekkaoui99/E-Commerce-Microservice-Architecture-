package com.hamzabekkaoui.customerservice.dto.response;

import lombok.Builder;

@Builder
public record CustomerResponse(
        Long id,
        String firstName,
        String lastName,
        String mail,
        String phoneNumber,
        String userName
) {
}
