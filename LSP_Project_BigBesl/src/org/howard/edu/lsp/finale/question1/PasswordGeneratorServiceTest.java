package org.howard.edu.lsp.finale.question1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit test suite for PasswordGeneratorService.
 * Tests singleton behavior, algorithm switching, and password generation requirements.
 */
public class PasswordGeneratorServiceTest {
    
    private PasswordGeneratorService service;
    
    /**
     * Sets up the test fixture before each test method.
     */
    @BeforeEach
    public void setup() {
        service = PasswordGeneratorService.getInstance();
    }
    
    /**
     * Verifies that getInstance() returns a non-null instance.
     */
    @Test
    public void checkInstanceNotNull() {
        assertNotNull(service, "getInstance() should return a non-null instance");
    }
    
    /**
     * Verifies true singleton behavior - that getInstance() always returns
     * the exact same object instance in memory.
     */
    @Test
    public void checkSingleInstanceBehavior() {
        PasswordGeneratorService second = PasswordGeneratorService.getInstance();
        assertSame(service, second, 
            "getInstance() should return the same instance (true singleton behavior)");
    }
    
    /**
     * Verifies that calling generatePassword() without setting an algorithm
     * throws IllegalStateException.
     */
    @Test
    public void generateWithoutSettingAlgorithmThrowsException() {
        PasswordGeneratorService s = PasswordGeneratorService.getInstance();
        // Note: Since singleton persists state, we need to ensure no algorithm is set
        // This test assumes a fresh service or proper reset between tests
        assertThrows(IllegalStateException.class, () -> {
            // Create a workaround by accessing a method that checks for null strategy
            s.generatePassword(10);
        }, "generatePassword() should throw IllegalStateException when no algorithm is set");
    }
    
    /**
     * Verifies that the basic algorithm generates passwords of correct length
     * containing only digits (0-9).
     */
    @Test
    public void basicAlgorithmGeneratesCorrectLengthAndDigitsOnly() {
        service.setAlgorithm("basic");
        String p = service.generatePassword(10);
        
        assertNotNull(p, "Generated password should not be null");
        assertEquals(10, p.length(), "Password length should be 10");
        assertTrue(p.matches("[0-9]+"), 
            "Basic algorithm password should contain only digits (0-9)");
    }
    
    /**
     * Verifies that the enhanced algorithm generates passwords of correct length
     * containing only alphanumeric characters (A-Z, a-z, 0-9).
     */
    @Test
    public void enhancedAlgorithmGeneratesCorrectCharactersAndLength() {
        service.setAlgorithm("enhanced");
        String p = service.generatePassword(12);
        
        assertNotNull(p, "Generated password should not be null");
        assertEquals(12, p.length(), "Password length should be 12");
        assertTrue(p.matches("[A-Za-z0-9]+"), 
            "Enhanced algorithm password should contain only alphanumeric characters");
    }
    
    /**
     * Verifies that the letters algorithm generates passwords containing
     * only letters (A-Z, a-z).
     */
    @Test
    public void lettersAlgorithmGeneratesLettersOnly() {
        service.setAlgorithm("letters");
        String p = service.generatePassword(8);
        
        assertNotNull(p, "Generated password should not be null");
        assertEquals(8, p.length(), "Password length should be 8");
        assertTrue(p.matches("[A-Za-z]+"), 
            "Letters algorithm password should contain only letters");
        assertFalse(p.matches(".*[0-9].*"), 
            "Letters algorithm password should not contain any digits");
    }
    
    /**
     * Verifies that switching algorithms changes the password generation behavior
     * and that each algorithm produces output matching its specification.
     */
    @Test
    public void switchingAlgorithmsChangesBehavior() {
        service.setAlgorithm("basic");
        String p1 = service.generatePassword(10);
        
        service.setAlgorithm("letters");
        String p2 = service.generatePassword(10);
        
        service.setAlgorithm("enhanced");
        String p3 = service.generatePassword(10);
        
        // Verify each password meets its algorithm's requirements
        assertTrue(p1.matches("[0-9]+"), 
            "Basic algorithm should produce digits only");
        assertTrue(p2.matches("[A-Za-z]+"), 
            "Letters algorithm should produce letters only");
        assertTrue(p3.matches("[A-Za-z0-9]+"), 
            "Enhanced algorithm should produce alphanumeric characters");
        
        // Verify all have correct length
        assertEquals(10, p1.length(), "Basic password should have length 10");
        assertEquals(10, p2.length(), "Letters password should have length 10");
        assertEquals(10, p3.length(), "Enhanced password should have length 10");
        
        // Verify letters password contains no digits
        assertFalse(p2.matches(".*[0-9].*"), 
            "Letters algorithm should not produce any digits");
    }
}