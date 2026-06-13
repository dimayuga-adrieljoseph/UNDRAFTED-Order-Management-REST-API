package com.pup.taguig.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pup.taguig.model.Product;

@Mapper
public interface ProductMapper {
    void insertProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(int id);
    void updateStock(Product product);
}
