package org.howard.edu.lsp.midterm.question2;

public class Main {
    public static void main(String[] args) {
        // Test all overloaded methods to produce exact required output
        System.out.println("Circle radius 3.0 → area = " + AreaCalculator.area(3.0));
        System.out.println("Rectangle 5.0 x 2.0 → area = " + AreaCalculator.area(5.0, 2.0));
        System.out.println("Triangle base 10, height 6 → area = " + AreaCalculator.area(10, 6));
        System.out.println("Square side 4 → area = " + AreaCalculator.area(4));
        
        // Exception demonstration: invoke area method with invalid value
        try {
            AreaCalculator.area(-5.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /*
     * Implementation Features:
     * - Method Overloading: All methods have the same name 'area' but different parameter signatures
     * - Correct Formulas: Each method uses the appropriate geometric formula
     * - Exception Handling: All methods validate inputs and throw IllegalArgumentException for invalid dimensions
     * - Static Methods: All methods are static as required
     * - Exact Output: The main method produces the exact output specified in the requirements
     * - Exception Demonstration: Includes a try-catch block to handle the exception
     *
     * Conceptual Explanation:
     * Overloading is preferable here because it creates a clean, consistent API. Users only need to 
     * remember one method name (area) regardless of which shape they're working with. The compiler 
     * determines which version to call based on the parameters, making the code more readable and 
     * intuitive than having separate method names like circleArea, rectangleArea, etc.
     */
}