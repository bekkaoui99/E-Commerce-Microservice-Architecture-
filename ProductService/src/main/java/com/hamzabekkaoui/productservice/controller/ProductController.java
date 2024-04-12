package com.hamzabekkaoui.productservice.controller;

import com.hamzabekkaoui.productservice.dto.request.CategoryRequest;
import com.hamzabekkaoui.productservice.dto.request.ProductRequest;
import com.hamzabekkaoui.productservice.dto.response.CategoryResponse;
import com.hamzabekkaoui.productservice.dto.response.ProductResponse;
import com.hamzabekkaoui.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {


    private final ProductService productService;

    @PostMapping
    public ProductResponse create(@RequestBody ProductRequest categoryRequest){
        return productService.create(categoryRequest);
    }

    @PostMapping("{id}")
    public ProductResponse update(
            @PathVariable Long id,
            @RequestBody ProductRequest categoryRequest
    ){
        return productService.update(id ,categoryRequest);
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable Long id){
        return productService.delete(id);
    }

    @GetMapping("{id}")
    public ProductResponse getProductByID(@PathVariable Long id){
        return productService.getProductByID(id);
    }

    @PostMapping("/ByName")
    public ProductResponse getProductByName(@RequestBody Map<String, String> request){
        String title = request.get("title");
        return productService.getProductByName(title);
    }

    @GetMapping("/list")
    public List<ProductResponse> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/page")
    public Page<ProductResponse> getProducts(
            @RequestParam(name = "pageNumber" ,defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize" ,defaultValue = "0") int pageSize
    ){
        return productService.getProducts(pageNumber , pageSize);
    }


    @GetMapping("/byCategory/list")
    public List<ProductResponse> getProductsByCategoryName(
            @RequestBody String categoryName
    ){
        return productService.getProductsByCategory(categoryName);
    }

    @GetMapping("/byCategory/page")
    public Page<ProductResponse> getProductsByCategoryName(
            @RequestBody String categoryName,
            @RequestParam(name = "pageNumber" ,defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize" ,defaultValue = "0") int pageSize
    ){
        return productService.getProductsByCategory(categoryName ,pageNumber , pageSize);
    }
}
