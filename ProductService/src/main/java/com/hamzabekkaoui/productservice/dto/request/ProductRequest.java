package com.hamzabekkaoui.productservice.dto.request;

import com.hamzabekkaoui.productservice.model.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;

@Builder
public record ProductRequest(
         String title,
         String description,
         Long quantity,
         Double price,
         String categoryName
) {
}
