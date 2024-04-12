package com.hamzabekkaoui.productservice.service;

import com.hamzabekkaoui.productservice.dto.request.CategoryRequest;
import com.hamzabekkaoui.productservice.dto.response.CategoryResponse;
import com.hamzabekkaoui.productservice.exception.ResourceAlreadyExistException;
import com.hamzabekkaoui.productservice.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {

    CategoryResponse create(CategoryRequest categoryRequest) throws ResourceAlreadyExistException, ResourceNotFoundException;
    CategoryResponse update(Long id ,CategoryRequest categoryRequest) throws ResourceNotFoundException;
    boolean delete(Long id) throws ResourceNotFoundException;
    CategoryResponse getCategoryByID(Long id) throws ResourceNotFoundException;
    CategoryResponse getCategoryByName(String name) throws ResourceNotFoundException;
    Page<CategoryResponse> getCategories(int pageNumber , int pageSize);
    List<CategoryResponse> getCategories();

}
