/**
 * Name: Bryce Ly
 * PricingStrategy interface - Strategy pattern for pricing rules
 */
package org.howard.edu.lsp.assignment3;

/**
 * Interface for pricing strategies
 * Allows different pricing rules to be applied to products
 */
public interface PricingStrategy {
    /**
     * Applies pricing rules to a product
     * @param product The product to apply pricing to
     */
    void applyPricing(Product product);
}