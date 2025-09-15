/**
 * Name: Bryce Ly
 * CSVDataLoader - CSV-specific implementation of DataLoader
 */
package org.howard.edu.lsp.assignment3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * CSV-specific data loader implementation
 * Handles writing product data to CSV files
 */
public class CSVDataLoader implements DataLoader {
    
    @Override
    public void load(List<Product> products, String outputPath) throws IOException {
        File outputFile = new File(outputPath);
        outputFile.getParentFile().mkdirs();
        
        try (FileWriter writer = new FileWriter(outputFile)) {
            writeHeader(writer);
            writeData(writer, products);
        }
        
        System.out.println("Transformed data written to: " + outputFile.getAbsolutePath());
    }
    
    /**
     * Writes the CSV header row
     */
    private void writeHeader(FileWriter writer) throws IOException {
        writer.write("ProductID,Name,Price,Category,PriceRange\n");
    }
    
    /**
     * Writes the product data rows
     */
    private void writeData(FileWriter writer, List<Product> products) throws IOException {
        for (Product product : products) {
            writer.write(product.toString() + "\n");
        }
    }
}
