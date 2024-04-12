package com.hamzabekkaoui.productservice.init;

import com.hamzabekkaoui.productservice.dto.request.CategoryRequest;
import com.hamzabekkaoui.productservice.dto.request.ProductRequest;
import com.hamzabekkaoui.productservice.dto.response.CategoryResponse;
import com.hamzabekkaoui.productservice.dto.response.ProductResponse;
import com.hamzabekkaoui.productservice.repository.CategoryRepository;
import com.hamzabekkaoui.productservice.repository.ProductRepository;
import com.hamzabekkaoui.productservice.service.CategoryService;
import com.hamzabekkaoui.productservice.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateData {

    @Bean
    public CommandLineRunner commandLineRunner(
            CategoryService categoryService,
            CategoryRepository categoryRepository,
            ProductService productService,
            ProductRepository productRepository
            
    ){
        return args -> {

            CategoryRequest computers = CategoryRequest.builder()
                    .name("computers")
                    .description("description ...")
                    .build();

            ProductRequest laptopHP = ProductRequest.builder()
                    .title("laptop HP")
                    .description("description ...")
                    .price(10000.5)
                    .quantity(100L)
                    .categoryName("computers")
                    .build();
            
            if(!categoryRepository.existsByName("computers")){
                CategoryResponse categoryResponse = categoryService.create(computers);
                if (!productRepository.existsByTitle("laptop HP")){
                    ProductResponse productResponse = productService.create(laptopHP);
                }
            }
            
            
        };
    }

}
