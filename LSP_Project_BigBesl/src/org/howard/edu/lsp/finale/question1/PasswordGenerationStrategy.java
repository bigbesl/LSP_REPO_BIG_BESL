package org.howard.edu.lsp.finale.question1;

/**
 * Strategy interface for password generation algorithms.
 * Implementations define specific password generation behaviors.
 */
public interface PasswordGenerationStrategy {
    
    /**
     * Generates a password of the specified length.
     * 
     * @param length the desired password length
     * @return the generated password string
     */
    String generate(int length);
}