/**
 * Name: Bryce Ly
 * DataExtractor interface - Defines contract for data extraction operations
 */
package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.util.List;

/**
 * Interface for data extraction operations
 * Allows for different data sources (CSV, JSON, XML, Database, etc.)
 */
public interface DataExtractor {
    /**
     * Extracts product data from the specified source
     * @param filePath Path to the data source
     * @return List of Product objects extracted from the source
     * @throws IOException if there are issues reading from the source
     */
    List<Product> extract(String filePath) throws IOException;
}