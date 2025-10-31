package com.example.spacecatsmarket.service;

import com.example.spacecatsmarket.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createProduct(Product product);

    List<Product> findAllProducts();

    Optional<Product> findProductById(Long id);

    Optional<Product> updateProduct(Long id, Product product);

    void deleteProduct(Long id);
}


