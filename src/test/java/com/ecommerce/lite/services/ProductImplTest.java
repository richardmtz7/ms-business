package com.ecommerce.lite.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ecommerce.lite.entities.Products;
import com.ecommerce.lite.repositories.IProductRepository;
import com.ecommerce.lite.service.impl.ProductServiceImpl;

public class ProductImplTest {
	@InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private IProductRepository iProductRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createProduct_success() throws Exception {
        Products product = new Products();
        product.setProductName("Test Product");
        product.setCharacteristics("Test Characteristics");

        when(iProductRepository.save(any(Products.class))).thenReturn(product);

        Products createdProduct = productService.createProduct(product);

        assertNotNull(createdProduct);
        assertEquals("Test Product", createdProduct.getProductName());
    }

    @Test
    public void createProduct_nullCharacteristics_throwsException() {
        Products product = new Products();
        product.setProductName("Test Product");
        product.setCharacteristics(null);

        Exception exception = assertThrows(Exception.class, () -> {
            productService.createProduct(product);
        });
        assertEquals("Some especifications cannot be null or empty", exception.getMessage());
    }

    @Test
    public void createProduct_nullProductName_throwsException() {
        Products product = new Products();
        product.setProductName(null);
        product.setCharacteristics("Test Characteristics");

        Exception exception = assertThrows(Exception.class, () -> {
            productService.createProduct(product);
        });
        assertEquals("Some especifications cannot be null or empty", exception.getMessage());
    }

    @Test
    public void deleteProduct_success() throws Exception {
        Integer productId = 1;
        Products product = new Products();
        product.setProductId(productId);

        when(iProductRepository.findById(productId)).thenReturn(Optional.of(product));

        productService.deleteProduct(productId);

        verify(iProductRepository, times(1)).deleteById(productId);
    }

    @Test
    public void deleteProduct_productDoesNotExist_doesNotDelete() throws Exception {
        Integer productId = 1;

        when(iProductRepository.findById(productId)).thenReturn(Optional.empty());

        productService.deleteProduct(productId);

        verify(iProductRepository, never()).deleteById(any(Integer.class));
    }

    @Test
    public void getProducts_success() {
        Products product1 = new Products();
        Products product2 = new Products();
        when(iProductRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Products> products = productService.getProducts();

        assertEquals(2, products.size());
    }
}
