package com.hamzabekkaoui.customerservice.dto.request;

import lombok.Builder;

@Builder
public record CustomerRequest(
         String firstName,
         String lastName,
         String userName,
         String mail,
         String phoneNumber,
         String city
) {
}
