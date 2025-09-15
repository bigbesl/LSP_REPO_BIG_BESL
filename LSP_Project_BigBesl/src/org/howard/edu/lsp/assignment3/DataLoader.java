/**
 * Name: Bryce Ly
 * DataLoader interface - Defines contract for data loading operations
 */
package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.util.List;

/**
 * Interface for data loading operations
 * Allows for different output formats (CSV, JSON, XML, Database, etc.)
 */
public interface DataLoader {
    /**
     * Loads transformed product data to the specified destination
     * @param products List of products to write
     * @param outputPath Path to the output destination
     * @throws IOException if there are issues writing to the destination
     */
    void load(List<Product> products, String outputPath) throws IOException;
}