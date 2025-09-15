/**
 * Name: Bryce Ly
 * StandardPricingStrategy - Standard pricing strategy with Electronics discount
 */
package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Standard pricing strategy with Electronics discount
 * Applies 10% discount to Electronics category products
 */
public class StandardPricingStrategy implements PricingStrategy {
    private static final double ELECTRONICS_DISCOUNT = 0.1; // 10% discount
    
    @Override
    public void applyPricing(Product product) {
        if ("Electronics".equalsIgnoreCase(product.getCategory())) {
            double discountedPrice = product.getPrice() * (1.0 - ELECTRONICS_DISCOUNT);
            product.setPrice(roundPrice(discountedPrice));
        }
    }
    
    /**
     * Rounds price to two decimal places using half-up rounding
     */
    private double roundPrice(double price) {
        BigDecimal bd = new BigDecimal(price);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}