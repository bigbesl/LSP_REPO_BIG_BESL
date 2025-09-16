
# ETL Pipeline for Products CSV

## Assumptions

1. **Input Format**: The input CSV file (`data/products.csv`) uses commas as delimiters and does not contain quoted fields or embedded commas.
2. **Data Types**: 
   - ProductID is an integer
   - Price is a decimal number
   - Name and Category are strings
3. **Header Handling**: The first row is always a header and should not be transformed.
4. **Error Handling**: The program handles missing files and empty input files gracefully.
5. **Rounding**: Uses `BigDecimal` with `RoundingMode.HALF_UP` for precise decimal rounding.

## Brief Design Notes

The program follows a modular ETL (Extract, Transform, Load) pipeline design:

1. **Extract**: Reads the CSV file line by line, parsing headers and data rows.
2. **Transform**: Applies the required transformations in the specified order:
   - Convert product names to uppercase
   - Apply 10% discount to Electronics products
   - Recategorize eligible Electronics products to "Premium Electronics"
   - Add PriceRange based on final price
3. **Load**: Writes the transformed data to a new CSV file with headers.
4. **Error Handling**: Includes specific handling for file not found and empty file scenarios.
5. **Summary Reporting**: Tracks and reports processing statistics.

The implementation uses Java's standard libraries for file I/O and data processing, avoiding any third-party dependencies.

## How to Run

1. **Project Structure**: Ensure your project has the following structure:
   ```
   JavaProjectRoot/
   ├── src/
   │   └── org/
   │       └── howard/
   │           └── edu/
   │               └── lsp/
   │                   └── assignment2/
   │                       └── ETLPipeline.java
   ├── data/
   │   ├── products.csv (input file)
   │   └── transformed_products.csv (will be created)
   ```

2. **Compilation**: From the JavaProjectRoot directory, compile the code:
   ```
   javac -d . src/org/howard/edu/lsp/assignment2/ETLPipeline.java
   ```

3. **Execution**: Run the program from the JavaProjectRoot directory:
   ```
   java org.howard.edu.lsp.assignment2.ETLPipeline
   ```

4. **Output**: The transformed data will be written to `data/transformed_products.csv`, and a processing summary will be printed to the console.

## AI Usage Documentation

### Summary
I used ChatGPT to help with specific implementation details for the CSV parsing and transformation logic, particularly around handling decimal rounding and price range categorization. The AI assisted with code structure suggestions and error handling approaches.

### Prompt
"I need to implement a CSV ETL pipeline in Java that processes product data. How should I handle rounding prices to two decimals using the 'round half up' method? Also, what's the best way to categorize prices into ranges like Low, Medium, High, and Premium?"

### AI Response Excerpt
"For rounding to two decimals with 'round half up' behavior, you should use BigDecimal with RoundingMode.HALF_UP. Here's an example:
```
BigDecimal price = new BigDecimal("123.456");
BigDecimal roundedPrice = price.setScale(2, RoundingMode.HALF_UP);
double result = roundedPrice.doubleValue();
```
For price categorization, you can implement a method that takes the final price and returns the appropriate category string based on the specified ranges."

### Usage of Response
I implemented the rounding exactly as suggested, using BigDecimal with RoundingMode.HALF_UP for precise decimal handling. For the price categorization, I created a helper method `getPriceRange()` that implements the specified logic with conditional checks for each price range. I extended the AI's suggestion to handle edge cases and ensure the categorization matched the exact requirements.

## External Source Documentation

### Stack Overflow - CSV Parsing
**Link**: https://stackoverflow.com/questions/36330636/how-to-read-csv-file-in-java-without-using-a-third-party-library  
**Usage**: Referenced for basic CSV parsing approach using String.split() method. Adapted to handle header rows and simple comma-separated values without quotes.

### Oracle Java Documentation - BigDecimal
**Link**: https://docs.oracle.com/javase/8/docs/api/java/math/BigDecimal.html  
**Usage**: Consulted for proper usage of BigDecimal.setScale() with RoundingMode.HALF_UP for precise decimal rounding as required by the assignment.

### Baeldung - Java File I/O
**Link**: https://www.baeldung.com/java-read-file  
**Usage**: Reviewed for best practices in file reading and writing with try-with-resources statements. Adapted to implement robust file handling with proper resource management.


