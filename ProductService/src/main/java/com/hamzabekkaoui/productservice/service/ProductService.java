package com.hamzabekkaoui.productservice.service;

import com.hamzabekkaoui.productservice.dto.request.ProductRequest;
import com.hamzabekkaoui.productservice.dto.response.ProductResponse;
import com.hamzabekkaoui.productservice.exception.ResourceAlreadyExistException;
import com.hamzabekkaoui.productservice.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    ProductResponse create(ProductRequest productRequest) throws ResourceAlreadyExistException , ResourceNotFoundException;
    ProductResponse update(Long id , ProductRequest productRequest) throws ResourceNotFoundException;
    boolean delete(Long id) throws ResourceNotFoundException;

    ProductResponse getProductByID(Long id) throws ResourceNotFoundException;
    ProductResponse getProductByName(String name) throws ResourceNotFoundException;
    Page<ProductResponse> getProducts(int pageNumber , int pageSize);
    List<ProductResponse> getProducts();

    Page<ProductResponse> getProductsByCategory(String categoryName ,int pageNumber , int pageSize) throws ResourceNotFoundException;
    List<ProductResponse> getProductsByCategory(String categoryName) throws ResourceNotFoundException;

}
