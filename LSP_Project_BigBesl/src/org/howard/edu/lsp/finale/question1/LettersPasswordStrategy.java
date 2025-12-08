package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * Letters-only password generation strategy.
 * Generates passwords containing only uppercase and lowercase letters (A-Z, a-z).
 */
public class LettersPasswordStrategy implements PasswordGenerationStrategy {
    
    private static final String LETTERS = 
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
        "abcdefghijklmnopqrstuvwxyz";
    
    private final Random random;
    
    /**
     * Constructs a LettersPasswordStrategy with a new Random instance.
     */
    public LettersPasswordStrategy() {
        this.random = new Random();
    }
    
    /**
     * Generates a password containing only letters.
     * 
     * @param length the desired password length
     * @return a string of random letters
     */
    @Override
    public String generate(int length) {
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(LETTERS.length());
            password.append(LETTERS.charAt(index));
        }
        return password.toString();
    }
}