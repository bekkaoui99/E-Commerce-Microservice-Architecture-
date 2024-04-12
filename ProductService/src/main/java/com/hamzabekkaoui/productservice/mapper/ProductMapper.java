package com.hamzabekkaoui.productservice.mapper;

import com.hamzabekkaoui.productservice.dto.request.ProductRequest;
import com.hamzabekkaoui.productservice.dto.response.ProductResponse;
import com.hamzabekkaoui.productservice.exception.ResourceNotFoundException;
import com.hamzabekkaoui.productservice.model.Category;
import com.hamzabekkaoui.productservice.model.Product;
import com.hamzabekkaoui.productservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductMapper {

    private final CategoryRepository categoryRepository;


    public Category getCategoryByName(String name){
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("category doesn't exist with this name :" + name));
    }
    public Product productRequestToProduct(ProductRequest productRequest){
        return Product.builder()
                .title(productRequest.title())
                .description(productRequest.description())
                .quantity(productRequest.quantity())
                .price(productRequest.price())
                .category(getCategoryByName(productRequest.categoryName()))
                .build();
    }

    public ProductResponse productToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .categoryName(product.getCategory().getName())
                .build();
    }
}
