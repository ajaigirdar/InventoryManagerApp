package org.myproject.model;

import org.junit.jupiter.api.Test;
import org.myproject.model.Product;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testGettersAndSetters() {
        // Create a product with sample values
        Product product = new Product(101, "Laptop", 10, new BigDecimal("999.99"));

        // Verify each getter returns the expected value
        assertEquals(101, product.getProductId());
        assertEquals("Laptop", product.getProductName());
        assertEquals(10, product.getQuantity());
        assertEquals(new BigDecimal("999.99"), product.getPrice());
    }

    @Test
    void testEqualsAndHashCode() {
        // Create two products with identical values
        Product product1 = new Product(101, "Laptop", 10, new BigDecimal("999.99"));
        Product product2 = new Product(101, "Laptop", 10, new BigDecimal("999.99"));

        // Verify equals() and hashCode() are consistent
        assertEquals(product1, product2);
        assertEquals(product1.hashCode(), product2.hashCode());
    }

    @Test
    void testToString() {
        // Create a product and check that its string representation contains key details
        Product product = new Product(101, "Laptop", 10, new BigDecimal("999.99"));
        String str = product.toString();
        assertTrue(str.contains("101"));
        assertTrue(str.contains("Laptop"));
        assertTrue(str.contains("10"));
        assertTrue(str.contains("999.99"));
    }
}
