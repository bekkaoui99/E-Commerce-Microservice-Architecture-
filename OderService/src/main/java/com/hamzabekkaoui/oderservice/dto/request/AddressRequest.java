package com.hamzabekkaoui.oderservice.dto.request;

import lombok.Builder;

@Builder
public record AddressRequest(
        String country,
        String city,
        String street,
        String postalCode
) {
}
