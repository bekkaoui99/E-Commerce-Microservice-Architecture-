package com.hamzabekkaoui.oderservice.dto.request;

import lombok.Builder;

@Builder
public record OrderItemRequest(
         String productName,
         Long quantity

) {
}
