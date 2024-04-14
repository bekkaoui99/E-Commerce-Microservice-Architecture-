package com.hamzabekkaoui.productservice.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String productImageUrl;
    private String description;
    private Long quantity;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "categoryId" , referencedColumnName = "id")
    private Category category;
}
