# Inventory Manager

This is a Java-based Inventory Manager application built with Spring Boot. It is a console (CLI) application that 
allows you to manage products in an inventory.<br>
The application demonstrates the use of Spring Boot, JPA (with 
single-table inheritance), and basic input validation.

## Features

- **Add Product:**  
  Add new products by providing a product ID, name, quantity, and price.
    - If an expiry date is provided, the product is treated as perishable (using single-table inheritance with a discriminator column).

- **View Products:**  
  Display all products in a formatted table.

- **Search Product:**  
  Search for a product by its ID or name (case insensitive).

- **Update Product:**  
  Update product details such as quantity, price, and (if applicable) expiry date.

- **Delete Product:**  
  Delete a product by its ID after confirmation.


## Prerequisites

- **Java JDK 11 or later**
- **Maven**
- **MySQL Database**

## Setup

1. **Database Configuration:**

   Create a database and table using the following SQL script:

   ```sql
   -- Create the database and use it
   CREATE DATABASE IF NOT EXISTS amazondb;
   USE amazondb;

   -- Create the products table with a product_type column for inheritance
   CREATE TABLE IF NOT EXISTS products (
       product_id INT PRIMARY KEY,
       product_name VARCHAR(255) NOT NULL,
       quantity INT NOT NULL,
       price DECIMAL(10,2) NOT NULL,
       expiry_date DATE DEFAULT NULL,
       product_type VARCHAR(20) NOT NULL
   );

   -- Insert sample data (optional)
   INSERT INTO products (product_id, product_name, quantity, price, expiry_date, product_type) VALUES
   (101, 'Laptop', 10, 999.99, NULL, 'PRODUCT'),
   (102, 'Mouse', 50, 25.50, NULL, 'PRODUCT'),
   (103, 'Keyboard', 30, 49.99, NULL, 'PRODUCT'),
   (104, 'Monitor', 15, 199.99, NULL, 'PRODUCT'),
   (105, 'Printer', 5, 149.99, NULL, 'PRODUCT'),
   (106, 'Desk Chair', 20, 89.99, NULL, 'PRODUCT'),
   (107, 'Smartphone', 12, 599.99, NULL, 'PRODUCT'),
   (108, 'Tablet', 8, 299.99, NULL, 'PRODUCT'),
   (109, 'USB Drive', 100, 9.99, NULL, 'PRODUCT'),
   (110, 'Headphones', 40, 59.99, NULL, 'PRODUCT');

2. **Update ```application.properties```**

In your ```src/main/resources``` folder, create or update the application.properties file with your database connection settings. For example:
````
spring.datasource.url=jdbc:mysql://localhost:3306/amazondb
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
````

## License
This project is provided for educational purposes. Modify and use it as needed.
```sh
git clone https://github.com/ajaigirdar/InventoryManagerApp.git
cd InventoryManagerApp