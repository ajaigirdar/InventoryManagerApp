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

    public Optional<Product> searchProduct(String searchTerm) {

        // If it is a number, we assume the user is searching by product ID.
        try {
            // Try to convert the search term to an integer.
            Integer id = Integer.parseInt(searchTerm);

            return productRepository.findById(id);
        } catch (NumberFormatException e) {
            // If the conversion fails, then the search term is not a number.
            // and we assume the user is searching by product name.
            // We get a list of all products, then check each product's name.
            return productRepository.findAll().stream()
                    // For each product, check if its name matches the search term
                    .filter(product -> product.getProductName().equalsIgnoreCase(searchTerm))
                    // Return the first matching product (if any)
                    .findFirst();
        }
    }

    public void deleteProduct(Integer productId) {
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
        } else {
            System.out.println("Product not found.");
        }
    }


    public Optional<Product> getProductById(Integer productId) {
        return productRepository.findById(productId);
    }


}