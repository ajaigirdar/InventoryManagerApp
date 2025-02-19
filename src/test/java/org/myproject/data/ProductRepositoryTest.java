package org.myproject.data;

import org.junit.jupiter.api.Test;
import org.myproject.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
// The following annotation tells Spring Boot to replace your real DataSource with an embedded one for testing.
// If you have H2 on the classpath, Spring Boot will use it.
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testSaveAndFind() {
        // Create a product instance
        Product product = new Product(101, "Laptop", 10, new BigDecimal("999.99"));

        // Save the product
        productRepository.save(product);

        // Retrieve the product by its ID
        Optional<Product> found = productRepository.findById(101);

        // Check that the product was found and has the expected name
        assertNotNull(found);
        assertTrue(found.isPresent());
        assertEquals("Laptop", found.get().getProductName());
    }
}
