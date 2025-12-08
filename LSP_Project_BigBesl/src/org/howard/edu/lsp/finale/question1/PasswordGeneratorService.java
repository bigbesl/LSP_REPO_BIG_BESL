package org.howard.edu.lsp.finale.question1;

/**
 * Service for generating passwords using various algorithms.
 * This class implements the Singleton pattern to ensure only one instance exists,
 * and the Strategy pattern to allow runtime selection of password generation algorithms.
 * 
 * DESIGN PATTERN DOCUMENTATION:
 * 
 * Pattern 1: SINGLETON PATTERN
 * - Ensures only one instance of PasswordGeneratorService exists throughout the application
 * - Provides global access point via getInstance()
 * - Satisfies requirement: "Provide a single shared access point" and "Only one instance of the service may exist"
 * 
 * Pattern 2: STRATEGY PATTERN
 * - Encapsulates different password generation algorithms as separate strategy objects
 * - Allows runtime selection and switching of algorithms via setAlgorithm()
 * - Satisfies requirements: "Support multiple approaches", "Allow caller to select approach at run time",
 *   "Make password-generation behavior swappable", "Support future expansion"
 * 
 * Why these patterns are appropriate:
 * - Singleton: Centralizes password generation configuration and prevents multiple conflicting instances
 * - Strategy: Provides flexibility to add new algorithms without modifying existing code (Open/Closed Principle)
 * - Together: They create a maintainable, extensible system that meets all architectural requirements
 */
public class PasswordGeneratorService {
    
    private static PasswordGeneratorService instance;
    private PasswordGenerationStrategy currentStrategy;
    
    /**
     * Private constructor to prevent direct instantiation.
     * Enforces Singleton pattern.
     */
    private PasswordGeneratorService() {
        this.currentStrategy = null;
    }
    
    /**
     * Returns the single instance of PasswordGeneratorService.
     * Creates the instance on first call (lazy initialization).
     * 
     * @return the singleton instance
     */
    public static PasswordGeneratorService getInstance() {
        if (instance == null) {
            instance = new PasswordGeneratorService();
        }
        return instance;
    }
    
    /**
     * Sets the password generation algorithm to use.
     * 
     * @param name the algorithm name: "basic", "enhanced", or "letters"
     * @throws IllegalArgumentException if algorithm name is not recognized
     */
    public void setAlgorithm(String name) {
        switch (name) {
            case "basic":
                currentStrategy = new BasicPasswordStrategy();
                break;
            case "enhanced":
                currentStrategy = new EnhancedPasswordStrategy();
                break;
            case "letters":
                currentStrategy = new LettersPasswordStrategy();
                break;
            default:
                throw new IllegalArgumentException("Unknown algorithm: " + name);
        }
    }
    
    /**
     * Generates a password of the specified length using the current algorithm.
     * 
     * @param length the desired password length
     * @return the generated password
     * @throws IllegalStateException if no algorithm has been set
     */
    public String generatePassword(int length) {
        if (currentStrategy == null) {
            throw new IllegalStateException("No algorithm has been set. Call setAlgorithm() first.");
        }
        return currentStrategy.generate(length);
    }
}