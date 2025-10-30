package com.example.spacecatsmarket.service;

import com.example.spacecatsmarket.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product create(Product product);

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Optional<Product> update(Long id, Product product);

    boolean delete(Long id);
}


