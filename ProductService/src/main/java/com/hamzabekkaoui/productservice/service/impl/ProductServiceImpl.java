package com.hamzabekkaoui.productservice.service.impl;

import com.hamzabekkaoui.productservice.dto.request.ProductRequest;
import com.hamzabekkaoui.productservice.dto.response.ProductResponse;
import com.hamzabekkaoui.productservice.exception.ResourceAlreadyExistException;
import com.hamzabekkaoui.productservice.exception.ResourceNotFoundException;

import com.hamzabekkaoui.productservice.mapper.ProductMapper;
import com.hamzabekkaoui.productservice.model.Category;
import com.hamzabekkaoui.productservice.model.Product;
import com.hamzabekkaoui.productservice.repository.CategoryRepository;
import com.hamzabekkaoui.productservice.repository.ProductRepository;
import com.hamzabekkaoui.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;


    @Override
    public ProductResponse create(ProductRequest productRequest) throws ResourceAlreadyExistException , ResourceNotFoundException {

        boolean doesProductExist = productRepository.existsByTitle(productRequest.title());
        if(doesProductExist) throw new ResourceAlreadyExistException("product already exists");

        Product product = productMapper.productRequestToProduct(productRequest);
        Product created = productRepository.save(product);

        return productMapper.productToProductResponse(created);
    }

    @Override
    public ProductResponse update(Long id, ProductRequest productRequest) throws ResourceNotFoundException {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("product doesn't exist"));

        if (productRequest.title() != null && !productRequest.title().equals(product.getTitle()))
            product.setTitle(productRequest.title());
        if (productRequest.description() != null && !productRequest.description().equals(product.getDescription()))
            product.setDescription(productRequest.description());
        if (productRequest.price() != null)
            product.setPrice(productRequest.price());
        if (productRequest.quantity() != null)
            product.setQuantity(productRequest.quantity());
        if (productRequest.categoryName() != null && !productRequest.categoryName().equals(product.getCategory().getName())){
            Category category = categoryRepository.findByName(productRequest.categoryName())
                    .orElseThrow(() -> new ResourceNotFoundException("category doesn't exist"));
            product.setCategory(category);
        }

        Product updated = productRepository.save(product);


        return productMapper.productToProductResponse(updated);
    }

    @Override
    public boolean delete(Long id) throws ResourceNotFoundException{
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("product doesn't exist"));
        productRepository.delete(product);
        return true;
    }

    @Override
    public ProductResponse getProductByID(Long id) throws ResourceNotFoundException{
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("product doesn't exist"));

        return productMapper.productToProductResponse(product);
    }

    @Override
    public ProductResponse getProductByName(String name) throws ResourceNotFoundException {
        Product product = productRepository.findByTitle(name)
                .orElseThrow(() -> new ResourceNotFoundException("product doesn't exist with name :" + name));
        return productMapper.productToProductResponse(product);
    }

    @Override
    public Page<ProductResponse> getProducts(int pageNumber, int pageSize) {
        Page<Product> products = productRepository.findAll(PageRequest.of(pageNumber, pageSize));

        List<ProductResponse> productResponseList = products.getContent()
                .stream()
                .map(productMapper::productToProductResponse)
                .collect(Collectors.toList());
        return new PageImpl<>(productResponseList , products.getPageable() , products.getTotalElements());
    }

    @Override
    public List<ProductResponse> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::productToProductResponse)
                .collect(Collectors.toList());

    }

    @Override
    public Page<ProductResponse> getProductsByCategory(String categoryName, int pageNumber, int pageSize) throws ResourceNotFoundException{
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new ResourceNotFoundException("category doesn't exist"));

        Page<Product> products = productRepository.findByCategory(category, PageRequest.of(pageNumber, pageSize));


        List<ProductResponse> productResponseList = products.getContent()
                .stream()
                .map(productMapper::productToProductResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(productResponseList , products.getPageable() , products.getTotalElements());

    }

    @Override
    public List<ProductResponse> getProductsByCategory(String categoryName) throws ResourceNotFoundException{
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new ResourceNotFoundException("category doesn't exist"));

        return productRepository.findByCategory(category)
                .stream()
                .map(productMapper::productToProductResponse)
                .collect(Collectors.toList());
    }
}
