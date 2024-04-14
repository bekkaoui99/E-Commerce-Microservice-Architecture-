package com.hamzabekkaoui.oderservice.dto.response;

import com.hamzabekkaoui.oderservice.model.OrderStatus;
import lombok.Builder;

import java.util.List;

@Builder
public record OrderDetailsResponse(
        Long id,
        String orderCode,
        OrderStatus orderStatus,
        String customerName,
        List<OrderItemResponse> orderItemResponseList,
        Double total
) {
}
