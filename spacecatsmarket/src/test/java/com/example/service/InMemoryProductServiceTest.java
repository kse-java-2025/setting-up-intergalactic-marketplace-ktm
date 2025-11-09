package com.example.spacecatsmarket.service;

import com.example.spacecatsmarket.domain.Product;
import com.example.spacecatsmarket.exception.DuplicateProductNameException;
import com.example.spacecatsmarket.exception.ProductNotFoundException;
import com.example.spacecatsmarket.service.InMemoryProductService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryProductServiceTest {

    private InMemoryProductService productService;

    @BeforeEach
    void setUp() {
        productService = new InMemoryProductService();
    }

    @Test
    void createProduct_shouldAddNewProductSuccessfully_allFields() {
        Product product = Product.builder()
                .name("Space Cat Toy")
                .description("A fun toy for cats")
                .price(10.0)
                .categoryId(1L)
                .build();

        Product saved = productService.createProduct(product);

        assertNotNull(saved.getId());
        assertEquals("Space Cat Toy", saved.getName());
        assertEquals("A fun toy for cats", saved.getDescription());
        assertEquals(10.0, saved.getPrice());
        assertEquals(1L, saved.getCategoryId());
        
        List<Product> allProducts = productService.findAllProducts();
        assertEquals(1, allProducts.size());
        Product stored = allProducts.get(0);
        
        assertEquals(saved.getId(), stored.getId());
        assertEquals(saved.getName(), stored.getName());
        assertEquals(saved.getDescription(), stored.getDescription());
        assertEquals(saved.getPrice(), stored.getPrice());
        assertEquals(saved.getCategoryId(), stored.getCategoryId());
    }

    @Test
    void createProduct_shouldThrowDuplicateProductNameException() {
        Product product1 = Product.builder()
                .name("Space laser Pointer")
                .description("Red laser")
                .price(5.0)
                .categoryId(1L)
                .build();

        productService.createProduct(product1);

        Product duplicate = Product.builder()
                .name("Space laser pointer") 
                .description("Duplicate product name")
                .price(6.0)
                .categoryId(1L)
                .build();

        assertThrows(DuplicateProductNameException.class,
                () -> productService.createProduct(duplicate));
    }

    @Test
    void findProductById_shouldThrowProductNotFoundException() {
        assertThrows(ProductNotFoundException.class,
                () -> productService.findProductById(999L));
    }

    @Test
    void updateProduct_shouldUpdateSuccessfully_allFields() {
        Product original = productService.createProduct(Product.builder()
                .name("Food Pack")
                .description("Dry cat food")
                .price(20.0)
                .categoryId(1L)
                .build());

        Product updatedData = Product.builder()
                .name("Premium Food Pack")
                .description("Better food")
                .price(25.0)
                .categoryId(2L)
                .build();

        Product updated = productService.updateProduct(original.getId(), updatedData);

        assertEquals(original.getId(), updated.getId());
        assertEquals("Premium Food Pack", updated.getName());
        assertEquals("Better food", updated.getDescription());
        assertEquals(25.0, updated.getPrice());
        assertEquals(2L, updated.getCategoryId());
    }
}
