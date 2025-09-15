/**
 * Name: Bryce Ly
 * StandardCategoryManager - Standard category manager implementing business rules
 */
package org.howard.edu.lsp.assignment3;

/**
 * Standard category manager implementing business rules
 * Recategorizes Electronics products over $500 as "Premium Electronics"
 */
public class StandardCategoryManager implements CategoryManager {
    private static final double PREMIUM_THRESHOLD = 500.00;
    
    @Override
    public void categorize(Product product) {
        if ("Electronics".equalsIgnoreCase(product.getCategory()) && 
            product.getPrice() > PREMIUM_THRESHOLD) {
            product.setCategory("Premium Electronics");
        }
    }
}