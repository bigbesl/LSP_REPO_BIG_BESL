package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/**
 * Enhanced password generation strategy using java.security.SecureRandom.
 * Generates passwords containing uppercase letters, lowercase letters, and digits.
 */
public class EnhancedPasswordStrategy implements PasswordGenerationStrategy {
    
    private static final String ALLOWED = 
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
        "abcdefghijklmnopqrstuvwxyz" +
        "0123456789";
    
    private final SecureRandom secureRandom;
    
    /**
     * Constructs an EnhancedPasswordStrategy with a new SecureRandom instance.
     */
    public EnhancedPasswordStrategy() {
        this.secureRandom = new SecureRandom();
    }
    
    /**
     * Generates a password containing uppercase letters, lowercase letters, and digits.
     * 
     * @param length the desired password length
     * @return a string of random alphanumeric characters
     */
    @Override
    public String generate(int length) {
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(ALLOWED.length());
            password.append(ALLOWED.charAt(index));
        }
        return password.toString();
    }
}