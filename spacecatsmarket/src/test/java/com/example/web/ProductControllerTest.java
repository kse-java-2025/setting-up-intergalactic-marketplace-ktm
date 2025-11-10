package com.example.spacecatsmarket.web;

import com.example.spacecatsmarket.domain.Product;
import com.example.spacecatsmarket.dto.product.ProductDto;
import com.example.spacecatsmarket.dto.product.ProductEntry;
import com.example.spacecatsmarket.dto.product.ProductListDto;
import com.example.spacecatsmarket.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("product-controller")
class ProductControllerTest {

    private static final String PRODUCTS_URL = "/api/v1/products";
    private static final String PRODUCTS_URL_TEMPLATE = PRODUCTS_URL + "/{id}";
    private static final String PRODUCT_NAME = "Intergalactic Space Laser";
    private static final String PRODUCT_DESCRIPTION = "High-power laser for adventurous space cats";
    private static final double PRODUCT_PRICE = 199.99;
    private static final long PRODUCT_CATEGORY_ID = 5L;
    private static final String UPDATED_PRODUCT_NAME = "Stellar Yarn Ball";
    private static final String UPDATED_PRODUCT_DESCRIPTION = "Glowing yarn for zero-gravity kittens";
    private static final double UPDATED_PRODUCT_PRICE = 249.49;
    private static final long UPDATED_CATEGORY_ID = 7L;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductService productService;

    @BeforeEach
    void setUp() {
        clearProducts();
    }

    @AfterEach
    void tearDown() {
        clearProducts();
    }

    @Test
    @DisplayName("POST /api/v1/products accepts valid payload and returns ProductEntry")
    @Tag("create")
    @SneakyThrows
    void createProduct_withValidPayload_returnsCreatedEntry() {
        ProductDto requestDto = buildProductDto();

        MvcResult result = mockMvc.perform(post(PRODUCTS_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", matchesPattern(PRODUCTS_URL + "/\\d+")))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        ProductEntry responseBody = objectMapper.readValue(result.getResponse().getContentAsString(), ProductEntry.class);
        assertThat(responseBody.getId()).isNotNull();
        assertThat(responseBody.getName()).isEqualTo(PRODUCT_NAME);
        assertThat(responseBody.getDescription()).isEqualTo(PRODUCT_DESCRIPTION);
        assertThat(responseBody.getPrice()).isEqualTo(PRODUCT_PRICE);
        assertThat(responseBody.getCategoryId()).isEqualTo(PRODUCT_CATEGORY_ID);

        assertThat(result.getResponse().getHeader("Location")).isEqualTo(PRODUCTS_URL + "/" + responseBody.getId());

        List<Product> storedProducts = productService.findAllProducts();
        assertThat(storedProducts).hasSize(1);
        Product stored = storedProducts.get(0);
        assertThat(stored.getId()).isEqualTo(responseBody.getId());
        assertThat(stored.getName()).isEqualTo(PRODUCT_NAME);
        assertThat(stored.getDescription()).isEqualTo(PRODUCT_DESCRIPTION);
        assertThat(stored.getPrice()).isEqualTo(PRODUCT_PRICE);
        assertThat(stored.getCategoryId()).isEqualTo(PRODUCT_CATEGORY_ID);
    }

    @Test
    @DisplayName("GET /api/v1/products returns list of products")
    @Tag("read")
    @SneakyThrows
    void getAllProducts_shouldReturnProductList() {
        Product created = createPersistedProduct(PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_PRICE, PRODUCT_CATEGORY_ID);

        MvcResult result = mockMvc.perform(get(PRODUCTS_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        ProductListDto response = objectMapper.readValue(result.getResponse().getContentAsString(), ProductListDto.class);
        assertThat(response.getProducts()).hasSize(1);
        ProductEntry entry = response.getProducts().get(0);
        assertThat(entry.getId()).isEqualTo(created.getId());
        assertThat(entry.getName()).isEqualTo(created.getName());
        assertThat(entry.getDescription()).isEqualTo(created.getDescription());
        assertThat(entry.getPrice()).isEqualTo(created.getPrice());
        assertThat(entry.getCategoryId()).isEqualTo(created.getCategoryId());
    }

    @Test
    @DisplayName("GET /api/v1/products/{id} returns single product entry")
    @Tag("read")
    @SneakyThrows
    void getProductById_shouldReturnProductEntry() {
        Product created = createPersistedProduct(PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_PRICE, PRODUCT_CATEGORY_ID);

        MvcResult result = mockMvc.perform(get(PRODUCTS_URL_TEMPLATE, created.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        ProductEntry responseEntry = objectMapper.readValue(result.getResponse().getContentAsString(), ProductEntry.class);
        assertThat(responseEntry.getId()).isEqualTo(created.getId());
        assertThat(responseEntry.getName()).isEqualTo(created.getName());
        assertThat(responseEntry.getDescription()).isEqualTo(created.getDescription());
        assertThat(responseEntry.getPrice()).isEqualTo(created.getPrice());
        assertThat(responseEntry.getCategoryId()).isEqualTo(created.getCategoryId());
    }

    @Test
    @DisplayName("GET /api/v1/products/{id} returns ProblemDetail when product missing")
    @Tag("read")
    @SneakyThrows
    void getProductById_whenMissing_returnsNotFoundProblemDetail() {
        mockMvc.perform(get(PRODUCTS_URL_TEMPLATE, 999L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.type").value("https://spacecatsmarket.com/problems/product-not-found"))
                .andExpect(jsonPath("$.title").value("Not Found"))
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.detail").value("Product with id 999 not found"))
                .andExpect(jsonPath("$.instance").value(PRODUCTS_URL));
    }

    @Test
    @DisplayName("PUT /api/v1/products/{id} updates product and returns entry")
    @Tag("update")
    @SneakyThrows
    void updateProduct_withValidPayload_returnsUpdatedEntry() {
        Product existing = createPersistedProduct(PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_PRICE, PRODUCT_CATEGORY_ID);
        ProductDto updateRequest = buildUpdatedProductDto();

        MvcResult result = mockMvc.perform(put(PRODUCTS_URL_TEMPLATE, existing.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        ProductEntry responseEntry = objectMapper.readValue(result.getResponse().getContentAsString(), ProductEntry.class);
        assertThat(responseEntry.getId()).isEqualTo(existing.getId());
        assertThat(responseEntry.getName()).isEqualTo(UPDATED_PRODUCT_NAME);
        assertThat(responseEntry.getDescription()).isEqualTo(UPDATED_PRODUCT_DESCRIPTION);
        assertThat(responseEntry.getPrice()).isEqualTo(UPDATED_PRODUCT_PRICE);
        assertThat(responseEntry.getCategoryId()).isEqualTo(UPDATED_CATEGORY_ID);

        Product stored = productService.findProductById(existing.getId());
        assertThat(stored.getName()).isEqualTo(UPDATED_PRODUCT_NAME);
        assertThat(stored.getDescription()).isEqualTo(UPDATED_PRODUCT_DESCRIPTION);
        assertThat(stored.getPrice()).isEqualTo(UPDATED_PRODUCT_PRICE);
        assertThat(stored.getCategoryId()).isEqualTo(UPDATED_CATEGORY_ID);
    }

    @Test
    @DisplayName("DELETE /api/v1/products/{id} removes product")
    @Tag("delete")
    @SneakyThrows
    void deleteProduct_shouldReturnNoContent() {
        Product existing = createPersistedProduct(PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_PRICE, PRODUCT_CATEGORY_ID);

        mockMvc.perform(delete(PRODUCTS_URL_TEMPLATE, existing.getId()))
                .andExpect(status().isNoContent());

        assertThat(productService.findAllProducts()).isEmpty();
    }

    @Test
    @DisplayName("POST /api/v1/products rejects invalid payload and returns ProblemDetail")
    @Tag("create")
    @SneakyThrows
    void createProduct_withInvalidPayload_returnsBadRequestProblemDetail() {
        ProductDto invalidRequest = buildProductDto().toBuilder()
                .name("")
                .build();

        mockMvc.perform(post(PRODUCTS_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.type").value("https://spacecatsmarket.com/problems/validation-error"))
                .andExpect(jsonPath("$.title").value("Bad Request"))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.detail").value("name: Product name is mandatory"))
                .andExpect(jsonPath("$.instance").value(PRODUCTS_URL));

        assertThat(productService.findAllProducts()).isEmpty();
    }

    private ProductDto buildProductDto() {
        return ProductDto.builder()
                .name(PRODUCT_NAME)
                .description(PRODUCT_DESCRIPTION)
                .price(PRODUCT_PRICE)
                .categoryId(PRODUCT_CATEGORY_ID)
                .build();
    }

    private ProductDto buildUpdatedProductDto() {
        return ProductDto.builder()
                .name(UPDATED_PRODUCT_NAME)
                .description(UPDATED_PRODUCT_DESCRIPTION)
                .price(UPDATED_PRODUCT_PRICE)
                .categoryId(UPDATED_CATEGORY_ID)
                .build();
    }

    private Product createPersistedProduct(String name, String description, double price, long categoryId) {
        return productService.createProduct(Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .categoryId(categoryId)
                .build());
    }

    private void clearProducts() {
        productService.findAllProducts().forEach(product -> productService.deleteProduct(product.getId()));
    }
}

