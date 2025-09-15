/**
 * Name: Bryce Ly
 * Product class - Enhanced with validation and business logic
 */
package org.howard.edu.lsp.assignment3;

/**
 * Represents a product record with all required fields
 * Enhanced with proper encapsulation and validation
 */
public class Product {
    private int productID;
    private String name;
    private double price;
    private String category;
    private String priceRange;
    
    public Product(int productID, String name, double price, String category) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.category = category;
        this.priceRange = "";
    }
    
    // Getters
    public int getProductID() { 
        return productID; 
    }
    
    public String getName() { 
        return name; 
    }
    
    public double getPrice() { 
        return price; 
    }
    
    public String getCategory() { 
        return category; 
    }
    
    public String getPriceRange() { 
        return priceRange; 
    }
    
    // Setters with validation
    public void setName(String name) {
        this.name = name != null ? name : "";
    }
    
    public void setPrice(double price) {
        this.price = Math.max(0, price); // Ensure non-negative price
    }
    
    public void setCategory(String category) {
        this.category = category != null ? category : "";
    }
    
    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange != null ? priceRange : "";
    }
    
    @Override
    public String toString() {
        return productID + "," + name + "," + String.format("%.2f", price) + 
               "," + category + "," + priceRange;
    }
    
    /**
     * Creates a deep copy of this product
     */
    public Product copy() {
        Product copy = new Product(this.productID, this.name, this.price, this.category);
        copy.setPriceRange(this.priceRange);
        return copy;
    }
}