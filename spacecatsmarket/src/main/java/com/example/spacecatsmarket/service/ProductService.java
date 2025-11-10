package com.example.spacecatsmarket.service;

import com.example.spacecatsmarket.domain.Product;

import java.util.List;
public interface ProductService {
    Product createProduct(Product product);

    List<Product> findAllProducts();

    Product findProductById(Long id);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);
}


