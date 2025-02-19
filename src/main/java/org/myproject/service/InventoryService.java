package org.myproject.service;

import org.myproject.data.ProductRepository;
import org.myproject.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class InventoryService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        if (productRepository.existsById(product.getProductId())) {
            System.out.println("Product with ID " + product.getProductId() + " already exists.");
        } else {
            productRepository.save(product);
        }
    }

    public void updateProduct(Product product) {
        if (productRepository.existsById(product.getProductId())) {
            productRepository.save(product);
        } else {
            System.out.println("Product not found.");
        }
    }


    public Optional<Product> getProductById(Integer productId) {
        return productRepository.findById(productId);
    }


}