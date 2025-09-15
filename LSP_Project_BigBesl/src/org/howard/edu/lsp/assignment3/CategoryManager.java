/**
 * Name: Bryce Ly
 * CategoryManager interface - Strategy pattern for category management
 */
package org.howard.edu.lsp.assignment3;

/**
 * Interface for category management
 * Allows different categorization rules to be applied to products
 */
public interface CategoryManager {
    /**
     * Applies categorization rules to a product
     * @param product The product to categorize
     */
    void categorize(Product product);
}