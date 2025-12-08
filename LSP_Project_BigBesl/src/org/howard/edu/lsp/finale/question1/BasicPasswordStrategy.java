package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * Basic password generation strategy using java.util.Random.
 * Generates passwords containing only digits (0-9).
 */
public class BasicPasswordStrategy implements PasswordGenerationStrategy {
    
    private static final String DIGITS = "0123456789";
    private final Random random;
    
    /**
     * Constructs a BasicPasswordStrategy with a new Random instance.
     */
    public BasicPasswordStrategy() {
        this.random = new Random();
    }
    
    /**
     * Generates a password containing only digits.
     * 
     * @param length the desired password length
     * @return a string of random digits
     */
    @Override
    public String generate(int length) {
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(DIGITS.length());
            password.append(DIGITS.charAt(index));
        }
        return password.toString();
    }
}