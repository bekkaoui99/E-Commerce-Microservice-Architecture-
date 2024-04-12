package com.hamzabekkaoui.productservice.controller;

import com.hamzabekkaoui.productservice.dto.request.CategoryRequest;
import com.hamzabekkaoui.productservice.dto.response.CategoryResponse;
import com.hamzabekkaoui.productservice.service.CategoryService;
import com.hamzabekkaoui.productservice.service.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public CategoryResponse create(@RequestBody CategoryRequest categoryRequest){
        return categoryService.create(categoryRequest);
    }

    @PostMapping("{id}")
    public CategoryResponse update(
            @PathVariable Long id,
            @RequestBody CategoryRequest categoryRequest
    ){
        return categoryService.update(id ,categoryRequest);
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable Long id){
        return categoryService.delete(id);
    }

    @GetMapping("{id}")
    public CategoryResponse getCategoryByID(@PathVariable Long id){
        return categoryService.getCategoryByID(id);
    }

    @GetMapping("/ByName")
    public CategoryResponse getCategoryByName(@RequestBody String title){
        return categoryService.getCategoryByName(title);
    }

    @GetMapping("/list")
    public List<CategoryResponse> getCategories(){
        return categoryService.getCategories();
    }

    @GetMapping("/page")
    public Page<CategoryResponse> getCategories(
            @RequestParam(name = "pageNumber" ,defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize" ,defaultValue = "0") int pageSize
    ){
        return categoryService.getCategories(pageNumber , pageSize);
    }
}
