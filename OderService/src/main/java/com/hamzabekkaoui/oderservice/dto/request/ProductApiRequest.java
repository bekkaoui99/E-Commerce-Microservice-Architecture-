package com.hamzabekkaoui.oderservice.dto.request;

import lombok.Builder;

@Builder
public record ProductApiRequest(
        String title
) {
}
