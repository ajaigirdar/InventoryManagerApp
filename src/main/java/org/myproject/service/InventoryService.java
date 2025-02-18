package org.myproject.service;

public class InventoryService {

    /*

          ATTRIBUTE productRepository

          METHOD getAllProducts() RETURNS List of Product
            RETURN productRepository.findAll()
          END METHOD


          METHOD addProduct(product: Product)
            IF productRepository.existsById(product.productId) THEN
               PRINT "Product with ID " + product.productId + " already exists."
            ELSE
               CALL productRepository.save(product)
               PRINT "Product added successfully."
            END IF
          END METHOD


          METHOD updateProduct(product: Product)
            IF productRepository.existsById(product.productId) THEN
               CALL productRepository.save(product)
               PRINT "Product updated successfully."
            ELSE
               PRINT "Product not found."
            END IF
          END METHOD


          METHOD updateProductByInput(productId: Integer, newName: String, newQty: int, newPrice: double)
            SET productOpt = productRepository.findById(productId)
            IF productOpt exists THEN
               SET product = productOpt.get()
               SET product.productName = newName
               SET product.quantity = newQty
               SET product.price = newPrice
               CALL productRepository.save(product)
               PRINT "Product updated successfully."
            ELSE
               PRINT "Product not found."
            END IF
          END METHOD


          METHOD deleteProduct(productId: Integer)
            IF productRepository.existsById(productId) THEN
               CALL productRepository.deleteById(productId)
               PRINT "Product deleted successfully."
            ELSE
               PRINT "Product not found."
            END IF
          END METHOD


          METHOD getProductById(productId: Integer) RETURNS Optional<Product>
            RETURN productRepository.findById(productId)
          END METHOD


          METHOD searchProduct(searchTerm: String) RETURNS Optional<Product>
            TRY:
               SET id = convert searchTerm to Integer
               RETURN productRepository.findById(id)
            CATCH conversion error:
               FOR EACH product in productRepository.findAll() DO
                  IF product.productName equalsIgnoreCase searchTerm THEN
                     RETURN Optional.of(product)
               END FOR
               RETURN Optional.empty()
          END METHOD



     */
}
