package com.hamzabekkaoui.productservice.repository;

import com.hamzabekkaoui.productservice.model.Category;
import com.hamzabekkaoui.productservice.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product , Long> {


    boolean existsByTitle(String title);

    Optional<Product> findByTitle(String title);
    Page<Product> findByCategory(Category category , Pageable pageable);
    List<Product> findByCategory(Category category);
}
