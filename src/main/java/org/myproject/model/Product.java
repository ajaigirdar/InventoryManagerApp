package org.myproject.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("PRODUCT")
public class Product implements Serializable {

    @Id
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private BigDecimal price;

    // Default constructor
    public Product() { }

    // Parameterized constructor
    public Product(Integer productId, String productName, Integer quantity, BigDecimal price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and setters
    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, quantity, price);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Product)) return false;
        Product other = (Product) obj;
        return Objects.equals(productId, other.productId) &&
                Objects.equals(productName, other.productName) &&
                Objects.equals(quantity, other.quantity) &&
                Objects.equals(price, other.price);
    }

    @Override
    public String toString() {
        return "Product [ID=" + productId +
                ", Name=" + productName +
                ", Quantity=" + quantity +
                ", Price=" + price + "]";
    }
}
