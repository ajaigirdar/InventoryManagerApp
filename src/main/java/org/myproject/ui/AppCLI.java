package org.myproject.ui;

public class AppCLI {

    /*
        CLASS AppCLI IMPLEMENTS CommandLineRunner
          ATTRIBUTE InventoryService
          ATTRIBUTE scanner


          METHOD run(args: Array of String)
            WHILE true DO
              CALL printMenu()
              SET choice = READ user input (trimmed)
              SWITCH (choice)
                 CASE "1":
                    CALL addProduct()
                 CASE "2":
                    CALL viewProducts()
                 CASE "3":
                    CALL searchProduct()
                 CASE "4":
                    CALL updateProduct()
                 CASE "5":
                    CALL deleteProduct()
                 CASE "6":
                    IF confirmAction("Are you sure you want to exit? (Y/N): ") THEN
                       PRINT "Exiting Program."
                       EXIT application (e.g., System.exit(0))
                    END IF
                 DEFAULT:
                    PRINT "Invalid option. Please try again."
              END SWITCH
              CALL pauseForUser()
            END WHILE
          END METHOD


          METHOD printMenu()
            PRINT "===== Inventory Manager ====="
            PRINT "1. Add Product"
            PRINT "2. View Products"
            PRINT "3. Search Product"
            PRINT "4. Update Product"
            PRINT "5. Delete Product"
            PRINT "6. Exit Program"
            PRINT "Enter your choice: "
          END METHOD


          METHOD addProduct()
            PRINT "===== Add Product ====="
            REPEAT
               SET id = promptForInt("Enter Product ID: ")
               IF InventoryService.getProductById(id) exists THEN
                 PRINT "Product with ID [id] already exists. Please enter a different ID."
               ELSE
                 EXIT loop
               END IF
            UNTIL a valid, unique id is entered
            SET name = promptForString("Enter Product Name: ")
            SET qty = promptForInt("Enter Quantity: ")
            SET price = promptForDouble("Enter Price: ")
            SET expiryDate = promptForDate("Enter Expiry Date (yyyy-mm-dd) or leave blank: ")
            CREATE new Product with (id, name, qty, price, expiryDate)
            CALL InventoryService.addProduct(product)
          END METHOD


          METHOD viewProducts()
            PRINT "===== Inventory Manager ====="
            PRINT "ID    | Name           | Quantity | Price"
            PRINT "---------------------------------------------"
            FOR EACH product IN InventoryService.getAllProducts() DO
               PRINT product details formatted (e.g., ID, Name, Quantity, Price)
            END FOR
            PRINT "---------------------------------------------"
          END METHOD


          METHOD searchProduct()
            PRINT "===== Search Product ====="
            SET term = promptForString("Enter Product ID or Name: ")
            SET result = InventoryService.searchProduct(term)
            IF result exists THEN
               PRINT "Product Found:"
               PRINT formatted product details (ID, Name, Quantity, Price)
               IF product.expiryDate exists THEN
                  PRINT "Expiry Date: " + product.expiryDate
               END IF
            ELSE
               PRINT "Product not found!"
            END IF
          END METHOD


          METHOD updateProduct()
            PRINT "===== Update Product ====="
            SET id = promptForInt("Enter Product ID: ")
            SET product = InventoryService.getProductById(id)
            IF product exists THEN
               PRINT "Current Details:"
               PRINT "Name: " + product.name
               PRINT "Quantity: " + product.quantity
               PRINT "Price: " + product.price
               SET newQty = promptForOptionalInt("Enter New Quantity (or press Enter to skip): ", product.quantity)
               SET newPrice = promptForOptionalDouble("Enter New Price (or press Enter to skip): ", product.price)
               UPDATE product.quantity to newQty
               UPDATE product.price to newPrice
               CALL InventoryService.updateProduct(product)
               PRINT "Product updated successfully!"
            ELSE
               PRINT "Product not found."
            END IF
          END METHOD


          METHOD deleteProduct()
            SET id = promptForInt("Enter Product ID to delete: ")
            IF InventoryService.getProductById(id) does NOT exist THEN
               PRINT "Product not found."
               RETURN
            END IF
            IF confirmAction("Are you sure you want to delete this product? (Y/N): ") THEN
               CALL InventoryService.deleteProduct(id)
               PRINT "Product deleted successfully."
            ELSE
               PRINT "Deletion cancelled."
            END IF
          END METHOD


          METHOD pauseForUser()
            PRINT "Press Enter to return to the main menu..."
            WAIT for user to press Enter
          END METHOD


          // Helper Methods

          METHOD promptForInt(prompt: String) RETURNS int
            LOOP:
               PRINT prompt
               SET input = READ user input (trimmed)
               TRY to convert input to integer:
                  IF successful THEN RETURN integer
               CATCH conversion error:
                  PRINT "Invalid input. Please enter a valid integer."
            END LOOP
          END METHOD


          METHOD promptForOptionalInt(prompt: String, currentValue: int) RETURNS int
            PRINT prompt
            SET input = READ user input (trimmed)
            IF input is empty THEN
               RETURN currentValue
            ELSE
               TRY to convert input to integer:
                  IF successful THEN RETURN integer
               CATCH conversion error:
                  PRINT "Invalid input. Using current value: " + currentValue
                  RETURN currentValue
            END IF
          END METHOD


          METHOD promptForDouble(prompt: String) RETURNS double
            LOOP:
               PRINT prompt
               SET input = READ user input (trimmed)
               TRY to convert input to double:
                  IF successful THEN RETURN double
               CATCH conversion error:
                  PRINT "Invalid input. Please enter a valid number (e.g., 12.34)."
            END LOOP
          END METHOD


          METHOD promptForOptionalDouble(prompt: String, currentValue: double) RETURNS double
            PRINT prompt
            SET input = READ user input (trimmed)
            IF input is empty THEN
               RETURN currentValue
            ELSE
               TRY to convert input to double:
                  IF successful THEN RETURN double
               CATCH conversion error:
                  PRINT "Invalid input. Using current value: " + currentValue
                  RETURN currentValue
            END IF
          END METHOD


          METHOD promptForString(prompt: String) RETURNS String
            LOOP:
               PRINT prompt
               SET input = READ user input (trimmed)
               IF input is not empty THEN RETURN input
               ELSE PRINT "Input cannot be empty. Please try again."
            END LOOP
          END METHOD


          METHOD promptForDate(prompt: String) RETURNS Date
            LOOP:
               PRINT prompt
               SET input = READ user input (trimmed)
               IF input is empty THEN RETURN null
               ELSE TRY to convert input to Date (format yyyy-mm-dd):
                  IF successful THEN RETURN Date
               CATCH conversion error:
                  PRINT "Invalid date format. Please use yyyy-mm-dd."
            END LOOP
          END METHOD


          METHOD confirmAction(prompt: String) RETURNS boolean
            LOOP:
               PRINT prompt
               SET input = READ user input (trimmed).toUpperCase()
               IF input equals "Y" or "YES" THEN RETURN true
               ELSE IF input equals "N" or "NO" THEN RETURN false
               ELSE PRINT "Invalid input. Please enter Y for Yes or N for No."
            END LOOP
          END METHOD


     */

}
