package com.hamzabekkaoui.oderservice.dto.response;

import lombok.Builder;

@Builder
public record AddressResponse(
        Long id,
        String country,
        String city,
        String street,
        String postalCode
) {
}
