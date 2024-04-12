package com.hamzabekkaoui.oderservice.repository;

import com.hamzabekkaoui.oderservice.dto.request.OrderItemRequest;
import com.hamzabekkaoui.oderservice.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
