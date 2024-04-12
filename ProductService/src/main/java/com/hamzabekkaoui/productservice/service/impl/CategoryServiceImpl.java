package com.hamzabekkaoui.productservice.service.impl;

import com.hamzabekkaoui.productservice.dto.request.CategoryRequest;
import com.hamzabekkaoui.productservice.dto.response.CategoryResponse;
import com.hamzabekkaoui.productservice.exception.ResourceAlreadyExistException;
import com.hamzabekkaoui.productservice.exception.ResourceNotFoundException;
import com.hamzabekkaoui.productservice.mapper.CategoryMapper;
import com.hamzabekkaoui.productservice.model.Category;
import com.hamzabekkaoui.productservice.repository.CategoryRepository;
import com.hamzabekkaoui.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse create(CategoryRequest categoryRequest) throws ResourceAlreadyExistException , ResourceNotFoundException{

        boolean exists = categoryRepository.existsByName(categoryRequest.name());
        if(exists) throw new ResourceAlreadyExistException("category already exist");

        Category category = categoryMapper.categoryRequestToCategory(categoryRequest);

        Category createdCategory = categoryRepository.save(category);

        return categoryMapper.categoryToCategoryResponse(createdCategory);
    }

    @Override
    public CategoryResponse update(Long id ,CategoryRequest categoryRequest) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category doesn't exist"));

        if(categoryRequest.name() != null && !categoryRequest.name().equals(category.getName()))
            category.setName(categoryRequest.name());
        if(categoryRequest.description() != null && !categoryRequest.description().equals(category.getDescription()))
            category.setDescription(categoryRequest.description());

        Category updated = categoryRepository.save(category);

        return categoryMapper.categoryToCategoryResponse(updated);
    }

    @Override
    public boolean delete(Long id) throws ResourceNotFoundException{
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category doesn't exist"));

        categoryRepository.delete(category);
        return true;
    }

    @Override
    public CategoryResponse getCategoryByID(Long id) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category doesn't exist"));

        return categoryMapper.categoryToCategoryResponse(category);
    }

    @Override
    public CategoryResponse getCategoryByName(String name) throws ResourceNotFoundException {
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("category doesn't exist"));
        return categoryMapper.categoryToCategoryResponse(category);
    }

    @Override
    public Page<CategoryResponse> getCategories(int pageNumber, int pageSize) {

        Page<Category> categories = categoryRepository.findAll(PageRequest.of(pageNumber, pageSize));
        List<CategoryResponse> categoryResponseList = categories.getContent()
                .stream()
                .map(categoryMapper::categoryToCategoryResponse)
                .collect(Collectors.toList());
        return new PageImpl<>(categoryResponseList , categories.getPageable() , categories.getTotalElements());
    }

    @Override
    public List<CategoryResponse> getCategories() {
        List<Category> categoryList = categoryRepository.findAll();

        return categoryList.stream()
                .map(categoryMapper::categoryToCategoryResponse)
                .collect(Collectors.toList());

    }
}
