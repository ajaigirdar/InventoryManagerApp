package org.myproject.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("PERISHABLE")
public class PerishableProduct extends Product {

    private LocalDate expiryDate;

    public PerishableProduct() {
        super();
    }

    // Parameterized constructor including productId and expiryDate
    public PerishableProduct(Integer productId, String productName, Integer quantity, BigDecimal price, LocalDate expiryDate) {
        super(productId, productName, quantity, price);
        this.expiryDate = expiryDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "PerishableProduct [ID=" + getProductId() +
                ", Name=" + getProductName() +
                ", Quantity=" + getQuantity() +
                ", Price=" + getPrice() +
                ", ExpiryDate=" + expiryDate + "]";
    }
}
