/**
 * Name: Bryce Ly
 * CSVDataExtractor - CSV-specific implementation of DataExtractor
 */
package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV-specific data extractor implementation
 * Handles reading and parsing CSV files with product data
 */
public class CSVDataExtractor implements DataExtractor {
    
    @Override
    public List<Product> extract(String filePath) throws IOException {
        File file = new File(filePath);
        validateFile(file);
        
        System.out.println("Reading data from: " + file.getAbsolutePath());
        return readFromFile(file);
    }
    
    /**
     * Validates that the file exists and is not empty
     */
    private void validateFile(File file) throws IOException {
        if (!file.exists()) {
            throw new IOException("Input file '" + file.getPath() + "' not found.");
        }
        
        if (file.length() == 0) {
            throw new IOException("Input file '" + file.getPath() + "' is empty.");
        }
    }
    
    /**
     * Reads product data from the CSV file
     */
    private List<Product> readFromFile(File file) throws IOException {
        List<Product> products = new ArrayList<>();
        int invalidRows = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String header = reader.readLine();
            if (header == null) {
                throw new IOException("Input file is empty.");
            }
            
            boolean usesTabs = header.contains("\t") && !header.contains(",");
            validateHeader(header);
            
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) {
                    System.err.println("Warning: Skipping empty line " + lineNumber);
                    continue;
                }
                
                Product product = parseLine(line, lineNumber, usesTabs);
                if (product != null) {
                    products.add(product);
                } else {
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
     * Validates the header format
     */
    private void validateHeader(String header) {
        if (!header.trim().equalsIgnoreCase("ProductID,Name,Price,Category") && 
            !header.trim().equalsIgnoreCase("ProductID\tName\tPrice\tCategory")) {
            System.err.println("Warning: Unexpected header format. Expected: ProductID,Name,Price,Category");
        }
    }
    
    /**
     * Parses a single line of CSV data into a Product object
     */
    private Product parseLine(String line, int lineNumber, boolean usesTabs) {
        String[] fields = usesTabs ? line.split("\t") : line.split(",");
        
        if (fields.length < 4) {
            System.err.println("Warning: Skipping invalid row (insufficient columns) at line " + lineNumber + ": " + line);
            return null;
        }
        
        try {
            int productID = Integer.parseInt(fields[0].trim());
            String name = fields[1].trim();
            double price = Double.parseDouble(fields[2].trim());
            String category = fields[3].trim();
            
            return new Product(productID, name, price, category);
        } catch (NumberFormatException e) {
            System.err.println("Warning: Skipping invalid row (number format error) at line " + lineNumber + ": " + line);
            return null;
        }
    }
}