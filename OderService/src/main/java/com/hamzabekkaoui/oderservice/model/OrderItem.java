package com.hamzabekkaoui.oderservice.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private Double price;
    private Long quantity;
    private Double total;

    @ManyToOne
    @JoinColumn(name = "orderId" , referencedColumnName = "id")
    private Order order;
}
