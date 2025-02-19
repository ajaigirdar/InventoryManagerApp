package org.myproject.service;

import org.myproject.data.ProductRepository;
import org.myproject.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class InventoryService {

    // The repository that accesses the database
    @Autowired
    private ProductRepository productRepository;


    // Retrieve all products from the database
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Add a new product to the inventory.
    // Checks first if a product with the same ID already exists.
    public void addProduct(Product product) {
        if (productRepository.existsById(product.getProductId())) {
            System.out.println("Product with ID " + product.getProductId() + " already exists.");
        } else {
            productRepository.save(product);
        }
    }

    // Update an existing product
    // Checks if the product exists before saving the changes.
    public void updateProduct(Product product) {
        if (productRepository.existsById(product.getProductId())) {
            productRepository.save(product);
        } else {
            System.out.println("Product not found.");
        }
    }


    // Search for a product by a search term.
    // If the search term is a number, it is treated as a product ID.
    // Otherwise, it searches by product name
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

    // Delete a product from the inventory.
    // Checks if the product exists before attempting deletion.
    public void deleteProduct(Integer productId) {
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
        } else {
            System.out.println("Product not found.");
        }
    }

    // Retrieve a product by its ID
    public Optional<Product> getProductById(Integer productId) {
        return productRepository.findById(productId);
    }


}