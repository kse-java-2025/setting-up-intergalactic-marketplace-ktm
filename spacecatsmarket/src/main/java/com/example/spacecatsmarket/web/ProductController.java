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

import java.net.URI;
import java.util.List;

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
        Product created = productService.createProduct(toCreate);
        ProductEntry body = productMapper.toEntry(created);
        return ResponseEntity.created(URI.create("/api/v1/products/" + created.getId())).body(body);
    }

    @GetMapping
    public ProductListDto getAll() {
        List<ProductEntry> entries = productMapper.toEntryList(productService.findAllProducts());
        return ProductListDto.builder().products(entries).build();
    }

    @GetMapping("/{id}")
    public ProductEntry getById(@PathVariable Long id) {
        Product product = productService.findProductById(id);
        return productMapper.toEntry(product);
    }

    @PutMapping("/{id}")
    public ProductEntry update(@PathVariable Long id, @Valid @RequestBody ProductDto productDto) {
        Product toUpdate = productMapper.toEntity(productDto);
        Product updated = productService.updateProduct(id, toUpdate);
        return productMapper.toEntry(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}


