package com.hamzabekkaoui.oderservice.dto.response;

import lombok.Builder;

@Builder
public record OrderItemResponse(
         Long id,
         Long productId,
         Long quantity,
         Double price,
         Double total

) {
}
