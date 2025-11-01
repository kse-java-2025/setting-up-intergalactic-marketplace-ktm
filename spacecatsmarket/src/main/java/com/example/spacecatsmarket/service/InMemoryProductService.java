package com.example.spacecatsmarket.service;

import com.example.spacecatsmarket.domain.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class InMemoryProductService implements ProductService {

    private final Map<Long, Product> idToProduct = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    @Override
    public Product createProduct(Product product) {
        long id = idSequence.getAndIncrement();
        Product toStore = Product.builder()
                .id(id)
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .categoryId(product.getCategoryId())
                .build();
        idToProduct.put(id, toStore);
        return copy(toStore);
    }

    @Override
    public List<Product> findAllProducts() {
        List<Product> products = new ArrayList<>();
        for (Map.Entry<Long, Product> entry : idToProduct.entrySet()) {
            products.add(copy(entry.getValue()));
        }
        return Collections.unmodifiableList(products);
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        Product product = idToProduct.get(id);
        return Optional.ofNullable(product == null ? null : copy(product));
    }

    @Override
    public Optional<Product> updateProduct(Long id, Product product) {
        if (!idToProduct.containsKey(id)) {
            return Optional.empty();
        }
        Product updated = Product.builder()
                .id(id)
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .categoryId(product.getCategoryId())
                .build();
        idToProduct.put(id, updated);
        return Optional.of(copy(updated));
    }

    @Override
    public void deleteProduct(Long id) {
        idToProduct.remove(id);
    }

    private Product copy(Product product) {
        return Product.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .categoryId(product.getCategoryId())
                .build();
    }
}


