package org.myproject.model;

import org.junit.jupiter.api.Test;
import org.myproject.model.PerishableProduct;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PerishableProductTest {

    @Test
    void testExpiryDate() {
        // Create a perishable product with an expiry date
        PerishableProduct perishable = new PerishableProduct(102, "Milk", 20, new BigDecimal("2.99"), LocalDate.of(2025, 12, 31));

        // Verify that the expiry date is set correctly
        assertEquals(LocalDate.of(2025, 12, 31), perishable.getExpiryDate());
    }

}
