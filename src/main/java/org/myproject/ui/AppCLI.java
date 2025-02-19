package org.myproject.ui;

import org.myproject.model.PerishableProduct;
import org.myproject.model.Product;
import org.myproject.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;


// AppCLI provides a command-line interface for the Inventory Manager application.
// It handles user input, displays a menu, and calls methods from the InventoryService.
@Component
public class AppCLI implements CommandLineRunner {

    @Autowired
    private InventoryService inventoryService;

    // Scanner for reading user input from the console
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) {
        boolean exit = false;

        // Main loop: display menu and process user choices until the user exits
        while (!exit) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> addProduct();
                case "2" -> viewProducts();
                case "3" -> searchProduct();
                case "4" -> updateProduct();
                case "5" -> deleteProduct();
                case "6" -> {
                    if (confirmAction("Are you sure you want to exit? (Y/N): ")) {
                        System.out.println("Exiting Program.");
                        System.exit(0);
                    }
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
            pauseForUser();
        }
        scanner.close();
    }


    // Prints the main menu options to the console
    private void printMenu() {
        System.out.println("\n===== Inventory Manager =====");
        System.out.println("1. Add Product");
        System.out.println("2. View Products");
        System.out.println("3. Search Product");
        System.out.println("4. Update Product");
        System.out.println("5. Delete Product");
        System.out.println("6. Exit Program");
        System.out.print("Enter your choice: ");
    }


    // Prompts the user to add a new product.
    // If an expiry date is provided, a PerishableProduct is created.
    // Otherwise, a standard Product is created.
    private void addProduct() {
        System.out.println("\n===== Add Product =====");

        // Prompt for product ID until a unique one is provided
        Integer productId;
        while (true) {
            productId = promptForInt("Enter Product ID: ");
            if (inventoryService.getProductById(productId).isPresent()) {
                System.out.println("Product with ID " + productId + " already exists. Please enter a different ID.");
            } else {
                break;
            }
        }

        String productName = promptForString("Enter Product Name: ");
        int quantity = promptForInt("Enter Quantity: ");
        BigDecimal price = promptForBigDecimal("Enter Price: ");
        Date expiryDate = promptForDate("Enter Expiry Date (yyyy-mm-dd) or leave blank: ");

        if (expiryDate != null) {

            // Create a perishable product if an expiry date is provided
            PerishableProduct perishableProduct = new PerishableProduct(productId, productName, quantity, price, expiryDate.toLocalDate());
            inventoryService.addProduct(perishableProduct);
            System.out.println("Perishable product added successfully.");

        } else {
            // Create a standard product if no expiry date is provided
            Product product = new Product(productId, productName, quantity, price);
            inventoryService.addProduct(product);
            System.out.println("Product added successfully.");
        }
    }


    // Displays all products in the inventory
    private void viewProducts() {
        System.out.println("\n===== Inventory Manager =====");
        System.out.println("ID    | Name           | Quantity | Price");
        System.out.println("---------------------------------------------");
        for (Product p : inventoryService.getAllProducts()) {
            System.out.printf("%-5d | %-14s | %-8d | $%.2f%n",
                    p.getProductId(),
                    p.getProductName(),
                    p.getQuantity(),
                    p.getPrice());
        }
        System.out.println("---------------------------------------------");
    }


    // Searches for a product by ID or name and displays the result
    private void searchProduct() {
        System.out.println("\n===== Search Product =====");
        String searchTerm = promptForString("Enter Product ID or Name: ");
        Optional<Product> result = inventoryService.searchProduct(searchTerm);
        if (result.isPresent()) {
            Product p = result.get();
            System.out.println("\nProduct Found:");
            System.out.printf("ID: %d%nName: %s%nQuantity: %d%nPrice: $%.2f%n",
                    p.getProductId(),
                    p.getProductName(),
                    p.getQuantity(),
                    p.getPrice());

            // If the product is perishable, display its expiry date
            if (p instanceof PerishableProduct) {
                PerishableProduct pp = (PerishableProduct) p;
                if (pp.getExpiryDate() != null) {
                    System.out.println("Expiry Date: " + pp.getExpiryDate());
                }
            }
            System.out.println();
        } else {
            System.out.println("\nProduct not found!");
        }
    }


    // Updates an existing product's details.
    // Prompts the user for new quantity, new price, and (if applicable) a new expiry date.
    private void updateProduct() {
        System.out.println("\n===== Update Product =====");
        int updateId = promptForInt("Enter Product ID: ");
        Optional<Product> optProduct = inventoryService.getProductById(updateId);

        if (optProduct.isPresent()) {
            Product p = optProduct.get();
            System.out.println("\nCurrent Details:");
            System.out.println("Name: " + p.getProductName());
            System.out.println("Quantity: " + p.getQuantity());
            System.out.printf("Price: $%.2f%n", p.getPrice());
            if (p instanceof PerishableProduct) {
                PerishableProduct pp = (PerishableProduct) p;
                System.out.println("Expiry Date: " + pp.getExpiryDate());
            }
            System.out.println();
            System.out.println();
            System.out.println();

            // Prompt for new values; if the user presses Enter, keep the current value.
            int newQty = promptForOptionalInt("Enter New Quantity (or press Enter to skip): ", p.getQuantity());
            BigDecimal newPrice = promptForOptionalBigDecimal("Enter New Price (or press Enter to skip): ", p.getPrice());
            p.setQuantity(newQty);
            p.setPrice(newPrice);

            if (p instanceof PerishableProduct) {
                PerishableProduct pp = (PerishableProduct) p;
                System.out.print("Enter New Expiry Date (YYYY-MM-DD) (or press Enter to skip): ");
                String expiryInput = scanner.nextLine().trim();
                if (!expiryInput.isEmpty()) {
                    pp.setExpiryDate(LocalDate.parse(expiryInput));
                }
            }

            inventoryService.updateProduct(p);
            System.out.println("\nProduct updated successfully!\n");
        } else {
            System.out.println("Product not found.");
        }
    }

    // Deletes a product from the inventory after confirming with the user
    private void deleteProduct() {
        int deleteId = promptForInt("Enter Product ID to delete: ");
        Optional<Product> optProduct = inventoryService.getProductById(deleteId);
        if (!optProduct.isPresent()) {
            System.out.println("Product not found.");
            return;
        }
        if (confirmAction("Are you sure you want to delete this product? (Y/N): ")) {
            inventoryService.deleteProduct(deleteId);
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }


    // Helper method: Pauses the program and waits for the user to press Enter before continuing
    private void pauseForUser() {
        System.out.println("Press Enter to return to the main menu...");
        scanner.nextLine();
    }

    // Helper method: prompt for an integer with validation.
    private int promptForInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    // Helper method: prompt for BigDecimal with validation.
    private BigDecimal promptForBigDecimal(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return new BigDecimal(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid decimal number (e.g., 12.34).");
            }
        }
    }

    // Helper method: prompt for an optional integer. Returns currentValue if input is blank.
    private int promptForOptionalInt(String prompt, int currentValue) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return currentValue;
        } else {
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Using current value: " + currentValue);
                return currentValue;
            }
        }
    }

    // Helper method: prompt for an optional BigDecimal. Returns currentValue if input is blank.
    private BigDecimal promptForOptionalBigDecimal(String prompt, BigDecimal currentValue) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return currentValue;
        } else {
            try {
                return new BigDecimal(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Using current value: " + currentValue);
                return currentValue;
            }
        }
    }

    // Helper method: prompt for a non-empty string.
    private String promptForString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    // Helper method: prompt for a date in yyyy-mm-dd format or blank.
    private Date promptForDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return null;
            }
            try {
                return Date.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date format. Please use yyyy-mm-dd.");
            }
        }
    }

    // Helper method: prompt for confirmation (Y/N).
    private boolean confirmAction(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("Y") || input.equals("YES")) {
                return true;
            } else if (input.equals("N") || input.equals("NO")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter Y for Yes or N for No.");
            }
        }
    }
}
