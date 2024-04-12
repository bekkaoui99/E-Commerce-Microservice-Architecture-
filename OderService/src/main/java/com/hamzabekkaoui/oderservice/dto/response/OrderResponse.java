package com.hamzabekkaoui.oderservice.dto.response;

import com.hamzabekkaoui.oderservice.dto.request.AddressRequest;
import com.hamzabekkaoui.oderservice.dto.request.OrderItemRequest;
import com.hamzabekkaoui.oderservice.model.OrderStatus;
import lombok.Builder;

import java.util.List;

@Builder
public record OrderResponse(
        Long id,
        String orderCode,
        OrderStatus orderStatus,
        Long customerId,
        Double total
) {
}
