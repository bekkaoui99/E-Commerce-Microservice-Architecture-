package com.hamzabekkaoui.oderservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "orders")

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderCode;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Double total;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @OneToOne
    @JoinColumn(name = "addressId" , referencedColumnName = "id")
    private Address address;

    private Long customerId;


}
