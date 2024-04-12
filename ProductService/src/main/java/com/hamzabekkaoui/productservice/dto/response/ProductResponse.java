package com.hamzabekkaoui.productservice.dto.response;

import lombok.Builder;

@Builder
public record ProductResponse(
        Long id,
        String title,
        String description,
        Long quantity,
        Double price,
        String categoryName
) {
}
