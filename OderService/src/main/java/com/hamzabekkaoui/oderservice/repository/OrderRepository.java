package com.hamzabekkaoui.oderservice.repository;

import com.hamzabekkaoui.oderservice.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order , Long> {

    Optional<Order> findByOrderCode(String orderCode);


    List<Order> findAllByCustomerId(Long customerId);
    Page<Order> findAllByCustomerId(Long customerId , Pageable pageable);
}
