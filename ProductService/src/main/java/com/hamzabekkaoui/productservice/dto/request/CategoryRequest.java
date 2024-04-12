package com.hamzabekkaoui.productservice.dto.request;

import lombok.Builder;

@Builder
public record CategoryRequest(
        String name,
        String description
) {
}
