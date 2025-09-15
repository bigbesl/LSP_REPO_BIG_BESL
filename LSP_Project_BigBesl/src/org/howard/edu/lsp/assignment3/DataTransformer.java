/**
 * Name: Bryce Ly
 * DataTransformer interface - Defines contract for data transformation operations
 */
package org.howard.edu.lsp.assignment3;

import java.util.List;

/**
 * Interface for data transformation operations
 * Allows for different transformation strategies and business rules
 */
public interface DataTransformer {
    /**
     * Transforms a list of products according to business rules
     * @param products List of products to transform
     * @return List of transformed products
     */
    List<Product> transform(List<Product> products);
}