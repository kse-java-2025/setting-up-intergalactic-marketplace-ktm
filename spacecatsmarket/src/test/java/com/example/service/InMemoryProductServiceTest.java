package com.example.spacecatsmarket.service;

import com.example.spacecatsmarket.domain.Product;
import com.example.spacecatsmarket.exception.DuplicateProductNameException;
import com.example.spacecatsmarket.exception.ProductNotFoundException;
import com.example.spacecatsmarket.service.InMemoryProductService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {InMemoryProductService.class})
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("InMemoryProductService Tests")
class InMemoryProductServiceTest {

    @Autowired
    private InMemoryProductService productService;

    @Test
    @org.junit.jupiter.api.Order(1)
    @DisplayName("Should create product successfully")
    void shouldCreateProduct() {
        Product input = Product.builder()
                .name("Space Cat Toy")
                .description("A fun toy for cats")
                .price(10.0)
                .categoryId(1L)
                .build();

        Product saved = assertDoesNotThrow(() -> productService.createProduct(input));

        assertNotNull(saved.getId());
        assertEquals("Space Cat Toy", saved.getName());
        assertEquals("A fun toy for cats", saved.getDescription());
        assertEquals(10.0, saved.getPrice());
        assertEquals(1L, saved.getCategoryId());

        List<Product> all = productService.findAllProducts();
        assertEquals(1, all.size());

        Product stored = all.get(0);

        assertEquals(saved.getId(), stored.getId());
        assertEquals(saved.getName(), stored.getName());
        assertEquals(saved.getDescription(), stored.getDescription());
        assertEquals(saved.getPrice(), stored.getPrice());
        assertEquals(saved.getCategoryId(), stored.getCategoryId());
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    @DisplayName("Should throw DuplicateProductNameException when name already exists (case-insensitive)")
    void shouldThrowDuplicateName() {

        Product p1 = Product.builder()
            .name("Space Laser Pointer")
            .description("Red laser")
            .price(5.0)
            .categoryId(1L)
            .build();

        productService.createProduct(p1);

        Product duplicate = Product.builder()
            .name("space laser pointer")
            .description("duplicate")
            .price(6.0)
            .categoryId(1L)
            .build();

        assertThrows(DuplicateProductNameException.class,
                () -> productService.createProduct(duplicate));
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    @DisplayName("Should throw ProductNotFoundException when ID does not exist")
    void shouldThrowProductNotFound() {

        assertThrows(ProductNotFoundException.class,
                () -> productService.findProductById(999L));
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    @DisplayName("Should update product successfully")
    void shouldUpdateProduct() {

        Product original = productService.createProduct(Product.builder()
            .name("Food Pack")
            .description("Dry cat food")
            .price(20.0)
            .categoryId(1L)
            .build()
        );

        Product updateData = Product.builder()
            .name("Premium Food Pack")
            .description("Better food")
            .price(25.0)
            .categoryId(2L)
            .build();

        Product updated = productService.updateProduct(original.getId(), updateData);

        assertEquals(original.getId(), updated.getId());
        assertEquals("Premium Food Pack", updated.getName());
        assertEquals("Better food", updated.getDescription());
        assertEquals(25.0, updated.getPrice());
        assertEquals(2L, updated.getCategoryId());
    }
}
