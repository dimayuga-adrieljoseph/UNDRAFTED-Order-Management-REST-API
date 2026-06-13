package com.pup.taguig.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pup.taguig.repository.ProductMapper;
import com.pup.taguig.model.Product;
import com.pup.taguig.dto.ProductDTO;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl {

    @Autowired
    private ProductMapper productMapper;

    public ProductDTO createProduct(ProductDTO request) {
        // Epic 2 Business Rules Validation
        if (request.getPrice() == null || request.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        if (request.getStock() == null || request.getStock() < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        
        productMapper.insertProduct(product);
        request.setId(product.getId()); // Attach the new DB id back to the response
        
        return request;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productMapper.getAllProducts();
        List<ProductDTO> dtos = new ArrayList<>();
        
        for(Product p : products) {
            ProductDTO dto = new ProductDTO();
            dto.setId(p.getId());
            dto.setName(p.getName());
            dto.setDescription(p.getDescription());
            dto.setPrice(p.getPrice());
            dto.setStock(p.getStock());
            dtos.add(dto);
        }
        return dtos;
    }

    public ProductDTO getProductById(int id) {
        Product p = productMapper.getProductById(id);
        if(p == null) return null;
        
        ProductDTO dto = new ProductDTO();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setDescription(p.getDescription());
        dto.setPrice(p.getPrice());
        dto.setStock(p.getStock());
        return dto;
    }
}