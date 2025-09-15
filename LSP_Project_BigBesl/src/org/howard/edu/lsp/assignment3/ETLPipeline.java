/**
 * Name: Bryce Ly
 * ETLPipeline - Main orchestrator class for the ETL process
 */
package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.util.List;

/**
 * Main ETL Pipeline orchestrator using dependency injection
 * Coordinates the Extract, Transform, and Load operations
 */
public class ETLPipeline {
    private final DataExtractor extractor;
    private final DataTransformer transformer;
    private final DataLoader loader;
    
    /**
     * Default constructor using standard implementations
     */
    public ETLPipeline() {
        this.extractor = new CSVDataExtractor();
        this.transformer = new ProductDataTransformer();
        this.loader = new CSVDataLoader();
    }
    
    /**
     * Constructor for dependency injection (useful for testing)
     * @param extractor The data extraction implementation to use
     * @param transformer The data transformation implementation to use
     * @param loader The data loading implementation to use
     */
    public ETLPipeline(DataExtractor extractor, DataTransformer transformer, DataLoader loader) {
        this.extractor = extractor;
        this.transformer = transformer;
        this.loader = loader;
    }
    
    /**
     * Main method to run the ETL pipeline
     */
    public static void main(String[] args) {
        ETLPipeline pipeline = new ETLPipeline();
        pipeline.run();
    }
    
    /**
     * Executes the complete ETL process
     */
    public void run() {
        String inputPath = "data/products.csv";
        String outputPath = "data/transformed_products.csv";
        
        System.out.println("Starting ETL Pipeline...");
        System.out.println("Input file: " + inputPath);
        System.out.println("Output file: " + outputPath);
        
        try {
            // Extract data
            List<Product> products = extractor.extract(inputPath);
            
            // Check if file was empty (only header)
            if (products.isEmpty()) {
                System.out.println("Warning: Input file exists but contains no data rows (only header)");
                System.out.println("ETL process completed with 0 rows processed.");
                return;
            }
            
            // Transform data
            List<Product> transformedProducts = transformer.transform(products);
            
            // Load data
            loader.load(transformedProducts, outputPath);
            
            // Print summary
            printSummary(products.size(), transformedProducts.size(), outputPath);
            
        } catch (IOException e) {
            handleError(e);
        }
    }
    
    /**
     * Prints a summary of the ETL process
     */
    private void printSummary(int rowsRead, int rowsTransformed, String outputPath) {
        System.out.println("\nETL Process Summary:");
        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Output written to: " + outputPath);
        
        if (rowsRead == 0) {
            System.out.println("Note: Input file contained only a header row or all rows were invalid.");
        }
    }
    
    /**
     * Handles errors and provides helpful guidance
     */
    private void handleError(IOException e) {
        System.err.println("\nERROR: " + e.getMessage());
        System.err.println("\nPlease ensure:");
        System.err.println("1. The 'data' directory exists");
        System.err.println("2. The 'data/products.csv' file exists with proper content");
        System.err.println("3. The file has the correct format: ProductID,Name,Price,Category");
        System.err.println("\nYou can create the data file by running DataFileCreator.java");
        System.exit(1);
    }
}