package com.example.spacecatsmarket.web;

import com.example.spacecatsmarket.domain.Product;
import com.example.spacecatsmarket.dto.product.ProductDto;
import com.example.spacecatsmarket.dto.product.ProductEntry;
import com.example.spacecatsmarket.dto.product.ProductListDto;
import com.example.spacecatsmarket.service.ProductService;
import com.example.spacecatsmarket.service.mapper.ProductMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ResponseEntity<ProductEntry> create(@Valid @RequestBody ProductDto productDto) {
        Product toCreate = productMapper.toEntity(productDto);
        Product created = productService.create(toCreate);
        ProductEntry body = toEntry(created.getId(), created);
        return ResponseEntity.created(URI.create("/api/v1/products/" + created.getId())).body(body);
    }

    @GetMapping
    public ProductListDto getAll() {
        List<ProductEntry> entries = productService.findAll().stream()
                .map(p -> toEntry(p.getId(), p))
                .collect(Collectors.toList());
        return ProductListDto.builder().products(entries).build();
    }

    @GetMapping("/{id}")
    public ProductEntry getById(@PathVariable Long id) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        return toEntry(id, product);
    }

    @PutMapping("/{id}")
    public ProductEntry update(@PathVariable Long id, @Valid @RequestBody ProductDto productDto) {
        Product toUpdate = productMapper.toEntity(productDto);
        Product updated = productService.update(id, toUpdate)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        return toEntry(id, updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    private ProductEntry toEntry(Long id, Product product) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Product id is missing");
        }
        return ProductEntry.builder()
                .id(id)
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .categoryId(product.getCategoryId())
                .build();
    }

    
}


