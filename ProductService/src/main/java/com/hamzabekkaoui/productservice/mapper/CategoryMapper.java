package com.hamzabekkaoui.productservice.mapper;

import com.hamzabekkaoui.productservice.dto.request.CategoryRequest;
import com.hamzabekkaoui.productservice.dto.response.CategoryResponse;
import com.hamzabekkaoui.productservice.model.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {

    public Category categoryRequestToCategory(CategoryRequest categoryRequest){
        return Category.builder()
                .name(categoryRequest.name())
                .description(categoryRequest.description())
                .build();
    }

    public CategoryResponse categoryToCategoryResponse(Category category){
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}
