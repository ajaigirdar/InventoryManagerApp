
CREATE DATABASE IF NOT EXISTS amazondb;
USE amazondb;

-- Products table with a product_type column for single-table inheritance
CREATE TABLE IF NOT EXISTS products (
    product_id INT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    expiry_date DATE DEFAULT NULL,
    product_type VARCHAR(20) NOT NULL  -- Discriminator column
);

-- (product_type = 'PRODUCT')
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

-- Any rows with a NULL or empty product_type are set to 'PRODUCT'
UPDATE products 
SET product_type = 'PRODUCT' 
WHERE product_type IS NULL OR product_type = '';

-- SET SQL_SAFE_UPDATES = 0;
