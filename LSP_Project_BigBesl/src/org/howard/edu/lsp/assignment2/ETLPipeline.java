/**
 * Name: Bryce Ly
 * 
 */
package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * ETL Pipeline for processing product CSV data
 * This program extracts data from data/products.csv, transforms it, and loads to data/transformed_products.csv
 * Requires data/products.csv to exist - run DataFileCreator first if needed
 */
public class ETLPipeline {

    /**
     * Represents a product record with all required fields
     */
    static class Product {
        int productID;
        String name;
        double price;
        String category;
        String priceRange;
        
        Product(int productID, String name, double price, String category) {
            this.productID = productID;
            this.name = name;
            this.price = price;
            this.category = category;
        }
        
        @Override
        public String toString() {
            return productID + "," + name + "," + String.format("%.2f", price) + 
                   "," + category + "," + priceRange;
        }
    }
    
    /**
     * Main method to run the ETL pipeline
     */
    public static void main(String[] args) {
        ETLPipeline pipeline = new ETLPipeline();
        pipeline.run();
    }
    
    /**
     * Executes the complete ETL process
     */
    public void run() {
        String inputPath = "data/products.csv";
        String outputPath = "data/transformed_products.csv";
        
        System.out.println("Starting ETL Pipeline...");
        System.out.println("Input file: " + inputPath);
        System.out.println("Output file: " + outputPath);
        
        try {
            // Extract data from CSV
            List<Product> products = extractData(inputPath);
            
            // Check if file was empty (only header)
            if (products.isEmpty()) {
                System.out.println("Warning: Input file exists but contains no data rows (only header)");
                System.out.println("ETL process completed with 0 rows processed.");
                return;
            }
            
            // Transform the data
            List<Product> transformedProducts = transformData(products);
            
            // Load transformed data to output file
            loadData(transformedProducts, outputPath);
            
            // Print summary
            printSummary(products.size(), transformedProducts.size(), outputPath);
            
        } catch (IOException e) {
            System.err.println("\nERROR: " + e.getMessage());
            System.err.println("\nPlease ensure:");
            System.err.println("1. The 'data' directory exists");
            System.err.println("2. The 'data/products.csv' file exists with proper content");
            System.err.println("3. The file has the correct format: ProductID,Name,Price,Category");
            System.err.println("\nYou can create the data file by running DataFileCreator.java");
            System.exit(1);
        }
    }
    
    /**
     * Extracts product data from CSV file
     */
    private List<Product> extractData(String filePath) throws IOException {
        File file = new File(filePath);
        
        // Check if file exists
        if (!file.exists()) {
            throw new IOException("Input file '" + filePath + "' not found.");
        }
        
        // Check if file is empty
        if (file.length() == 0) {
            throw new IOException("Input file '" + filePath + "' is empty.");
        }
        
        System.out.println("Reading data from: " + file.getAbsolutePath());
        return readFromFile(file);
    }
    
    /**
     * Reads data from File with support for both comma and tab delimiters
     */
    private List<Product> readFromFile(File file) throws IOException {
        List<Product> products = new ArrayList<>();
        int invalidRows = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // Read and validate header row
            String header = reader.readLine();
            if (header == null) {
                throw new IOException("Input file is empty.");
            }
            
            // Check if header uses tabs instead of commas
            boolean usesTabs = header.contains("\t") && !header.contains(",");
            
            if (!header.trim().equalsIgnoreCase("ProductID,Name,Price,Category") && 
                !header.trim().equalsIgnoreCase("ProductID\tName\tPrice\tCategory")) {
                System.err.println("Warning: Unexpected header format. Expected: ProductID,Name,Price,Category");
            }
            
            // Read data rows
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) {
                    System.err.println("Warning: Skipping empty line " + lineNumber);
                    continue;
                }
                
                // Use appropriate delimiter
                String[] fields;
                if (usesTabs) {
                    fields = line.split("\t");
                } else {
                    fields = line.split(",");
                }
                
                if (fields.length < 4) {
                    System.err.println("Warning: Skipping invalid row (insufficient columns) at line " + lineNumber + ": " + line);
                    invalidRows++;
                    continue;
                }
                
                try {
                    int productID = Integer.parseInt(fields[0].trim());
                    String name = fields[1].trim();
                    double price = Double.parseDouble(fields[2].trim());
                    String category = fields[3].trim();
                    
                    products.add(new Product(productID, name, price, category));
                } catch (NumberFormatException e) {
                    System.err.println("Warning: Skipping invalid row (number format error) at line " + lineNumber + ": " + line);
                    invalidRows++;
                }
            }
            
            if (invalidRows > 0) {
                System.out.println("Skipped " + invalidRows + " invalid rows during extraction.");
            }
        }
        
        return products;
    }
    
    /**
     * Applies all required transformations to product data
     */
    private List<Product> transformData(List<Product> products) {
        List<Product> transformed = new ArrayList<>();
        
        for (Product product : products) {
            Product transformedProduct = transformProduct(product);
            transformed.add(transformedProduct);
        }
        
        return transformed;
    }
    
    /**
     * Applies transformations to a single product
     */
    private Product transformProduct(Product product) {
        // Create a copy to avoid modifying the original
        Product transformed = new Product(
            product.productID, 
            product.name, 
            product.price, 
            product.category
        );
        
        // 1. Convert name to uppercase
        transformed.name = transformed.name.toUpperCase();
        
        // 2. Apply discount for Electronics
        if ("Electronics".equalsIgnoreCase(transformed.category)) {
            double discountedPrice = transformed.price * 0.9;
            transformed.price = roundPrice(discountedPrice);
        }
        
        // 3. Recategorize Premium Electronics
        if ("Electronics".equalsIgnoreCase(transformed.category) && transformed.price > 500.00) {
            transformed.category = "Premium Electronics";
        }
        
        // 4. Add PriceRange based on final price
        transformed.priceRange = determinePriceRange(transformed.price);
        
        return transformed;
    }
    
    /**
     * Rounds price to two decimal places using half-up rounding
     */
    private double roundPrice(double price) {
        BigDecimal bd = new BigDecimal(price);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    /**
     * Determines the price range category based on the price
     */
    private String determinePriceRange(double price) {
        if (price <= 10.00) {
            return "Low";
        } else if (price <= 100.00) {
            return "Medium";
        } else if (price <= 500.00) {
            return "High";
        } else {
            return "Premium";
        }
    }
    
    /**
     * Writes transformed data to output CSV file
     */
    private void loadData(List<Product> products, String outputPath) throws IOException {
        // Ensure data directory exists
        File outputFile = new File(outputPath);
        outputFile.getParentFile().mkdirs();
        
        try (FileWriter writer = new FileWriter(outputFile)) {
            // Write header
            writer.write("ProductID,Name,Price,Category,PriceRange\n");
            
            // Write data rows
            for (Product product : products) {
                writer.write(product.toString() + "\n");
            }
        }
        
        System.out.println("Transformed data written to: " + outputFile.getAbsolutePath());
    }
    
    /**
     * Prints a summary of the ETL process
     */
    private void printSummary(int rowsRead, int rowsTransformed, String outputPath) {
        System.out.println("\nETL Process Summary:");
        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Output written to: " + outputPath);
        
        if (rowsRead == 0) {
            System.out.println("Note: Input file contained only a header row or all rows were invalid.");
        }
    }
}