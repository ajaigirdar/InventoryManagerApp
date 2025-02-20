package org.myproject.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.myproject.data.ProductRepository;
import org.myproject.model.Product;
import org.myproject.service.InventoryService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventoryServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private InventoryService inventoryService;

    @BeforeEach
    void setUp() {
        // Initialize Mockito annotations
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddProductWhenNotExist() {
        Product product = new Product(101, "Laptop", 10, new BigDecimal("999.99"));
        // Simulate that no product with ID 101 exists
        when(productRepository.existsById(101)).thenReturn(false);
        when(productRepository.save(product)).thenReturn(product);

        inventoryService.addProduct(product);

        // Verify that the product was saved
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testAddProductWhenExists() {
        Product product = new Product(101, "Laptop", 10, new BigDecimal("999.99"));
        // Simulate that product with ID 101 already exists
        when(productRepository.existsById(101)).thenReturn(true);

        inventoryService.addProduct(product);

        // Verify that save() was never called because the product exists
        verify(productRepository, never()).save(product);
    }

    @Test
    void testSearchProductById() {
        Product product = new Product(101, "Laptop", 10, new BigDecimal("999.99"));
        // Simulate finding the product by ID
        when(productRepository.findById(101)).thenReturn(Optional.of(product));

        Optional<Product> result = inventoryService.searchProduct("101");
        assertTrue(result.isPresent());
        assertEquals("Laptop", result.get().getProductName());
    }

    @Test
    void testSearchProductByName() {
        Product product = new Product(101, "Laptop", 10, new BigDecimal("999.99"));
        // Simulate getting all products, then filtering by name
        when(productRepository.findAll()).thenReturn(Arrays.asList(product));

        Optional<Product> result = inventoryService.searchProduct("laptop");
        assertTrue(result.isPresent());
        assertEquals(101, result.get().getProductId());
    }

    @Test
    void testDeleteProduct() {
        // Simulate that product with ID 101 exists
        when(productRepository.existsById(101)).thenReturn(true);

        inventoryService.deleteProduct(101);

        // Verify that deleteById was called
        verify(productRepository, times(1)).deleteById(101);
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product(101, "Laptop", 10, new BigDecimal("999.99"));
        // Simulate that product exists and is saved successfully
        when(productRepository.existsById(101)).thenReturn(true);
        when(productRepository.save(product)).thenReturn(product);

        inventoryService.updateProduct(product);

        // Verify that the product was updated (saved)
        verify(productRepository, times(1)).save(product);
    }
}
