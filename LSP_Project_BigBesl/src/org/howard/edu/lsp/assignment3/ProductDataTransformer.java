/**
 * Name: Bryce Ly
 * ProductDataTransformer - Product-specific data transformer implementing business rules
 */
package org.howard.edu.lsp.assignment3;

import java.util.ArrayList;
import java.util.List;

/**
 * Product-specific data transformer implementing business rules
 * Coordinates name transformation, pricing, categorization, and price range assignment
 */
public class ProductDataTransformer implements DataTransformer {
    private final PricingStrategy pricingStrategy;
    private final CategoryManager categoryManager;
    
    public ProductDataTransformer() {
        this.pricingStrategy = new StandardPricingStrategy();
        this.categoryManager = new StandardCategoryManager();
    }
    
    /**
     * Constructor for dependency injection (useful for testing different strategies)
     */
    public ProductDataTransformer(PricingStrategy pricingStrategy, CategoryManager categoryManager) {
        this.pricingStrategy = pricingStrategy;
        this.categoryManager = categoryManager;
    }
    
    @Override
    public List<Product> transform(List<Product> products) {
        List<Product> transformed = new ArrayList<>();
        
        for (Product product : products) {
            Product transformedProduct = transformProduct(product);
            transformed.add(transformedProduct);
        }
        
        return transformed;
    }
    
    /**
     * Applies all transformations to a single product
     */
    private Product transformProduct(Product product) {
        Product transformed = product.copy();
        
        // Apply transformations in sequence
        transformed.setName(transformed.getName().toUpperCase());
        pricingStrategy.applyPricing(transformed);
        categoryManager.categorize(transformed);
        transformed.setPriceRange(determinePriceRange(transformed.getPrice()));
        
        return transformed;
    }
    
    /**
     * Determines the price range category based on the price
     */
    private String determinePriceRange(double price) {
        if (price <= 10.00) return "Low";
        else if (price <= 100.00) return "Medium";
        else if (price <= 500.00) return "High";
        else return "Premium";
    }
}