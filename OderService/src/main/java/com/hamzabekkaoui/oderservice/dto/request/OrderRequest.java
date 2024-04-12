package com.hamzabekkaoui.oderservice.dto.request;

import lombok.Builder;

import java.util.List;

@Builder
public record OrderRequest(
        String userName,
        AddressRequest addressRequest,
        List<OrderItemRequest> orderItemRequests
) {
}
