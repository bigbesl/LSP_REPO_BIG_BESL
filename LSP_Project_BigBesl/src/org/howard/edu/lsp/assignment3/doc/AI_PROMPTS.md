# AI Assistance Report for Assignment 3: Object-Oriented ETL Pipeline Redesign

**Student:** Bryce Ly  
**Assignment:** CSCI 363 Assignment 3  
**Date:** September 2025

## Overview

This document outlines how I used generative AI (Claude) to help redesign my Assignment 2 ETL Pipeline into a more object-oriented solution. The AI assisted with brainstorming OOP design patterns, suggesting code structure improvements, and helping implement proper separation of concerns.

## AI Prompts Used

### Initial Prompt
```
CSCI 363 – Assignment 3: Object-Oriented Redesign of Your ETL Pipeline
**Due: Thursday, September 25th, 11:59PM**
Purpose
In Assignment 2, you built an ETL pipeline in Java. In this assignment, you will redesign your solution to be more object-oriented. The goal is to practice object-oriented decomposition, not perfection. You must also use a generative AI assistant to help with the redesign and explain how you used or adapted its suggestions.

[Followed by my original Assignment 2 ETL Pipeline code]
```

**AI Response Summary:** The AI analyzed my monolithic ETL pipeline and suggested breaking it into multiple classes with clear responsibilities. It identified that my original code mixed concerns (file I/O, data transformation, business rules, error handling) and proposed using interfaces, strategy patterns, and proper encapsulation.

### Follow-up Prompt
```
its supposed to be split up into multiple classes
```

**AI Response Summary:** The AI understood that I needed separate `.java` files for each class rather than everything in one file. It then created 12 separate class files, each with a single responsibility and proper object-oriented design.

### Error Correction Prompt
```
there is an error in product.java from the line: 
public static void main(String[] args) {
ETLPipeline pipeline = new ETLPipeline();
pipeline.run();
}
```

**AI Response Summary:** The AI immediately recognized the error - the `main` method belonged in `ETLPipeline.java`, not `Product.java`. It corrected the `Product` class to focus solely on being a data model.

### Final Documentation Prompt
```
i need to make an md file containing the prompts that i asked the ai that i used please help me write the contents of that
```

## How AI Suggestions Were Used and Adapted

### 1. **Design Pattern Suggestions - Fully Adopted**
- **AI Suggested:** Strategy Pattern for pricing and categorization rules
- **Implementation:** Created `PricingStrategy` and `CategoryManager` interfaces with concrete implementations
- **Benefit:** Makes business rules easily extensible and testable

### 2. **Interface-Based Design - Fully Adopted**
- **AI Suggested:** `DataExtractor`, `DataTransformer`, and `DataLoader` interfaces
- **Implementation:** Separated concerns into extraction, transformation, and loading layers
- **Benefit:** Allows for different file formats and transformation strategies

### 3. **Encapsulation Improvements - Adapted**
- **AI Suggested:** Private fields with getters/setters in Product class
- **Adaptation:** Added validation in setters (non-negative prices, null checks)
- **Benefit:** Better data integrity and error prevention

### 4. **Dependency Injection - Fully Adopted**
- **AI Suggested:** Constructor injection in `ETLPipeline` for testability
- **Implementation:** Added both default constructor and injection constructor
- **Benefit:** Makes the code more testable and flexible

### 5. **Class Responsibility Separation - Fully Adopted**
- **AI Suggested:** Single Responsibility Principle for each class
- **Implementation:** 12 separate classes, each with one clear purpose
- **Benefit:** Improved maintainability and code organization

## Classes Created from AI Suggestions

1. **Product.java** - Data model with encapsulation
2. **DataExtractor.java** - Interface for data extraction
3. **CSVDataExtractor.java** - CSV-specific extraction implementation
4. **DataTransformer.java** - Interface for data transformation
5. **ProductDataTransformer.java** - Product transformation coordinator
6. **PricingStrategy.java** - Interface for pricing rules
7. **StandardPricingStrategy.java** - Electronics discount implementation
8. **CategoryManager.java** - Interface for categorization rules
9. **StandardCategoryManager.java** - Premium electronics categorization
10. **DataLoader.java** - Interface for data loading
11. **CSVDataLoader.java** - CSV output implementation
12. **ETLPipeline.java** - Main orchestrator class

## Key Improvements Achieved

### Before (Assignment 2)
- Single monolithic class handling all responsibilities
- Mixed concerns (I/O, business logic, formatting)
- Difficult to test individual components
- Hard to extend with new features

### After (Assignment 3)
- 12 focused classes with single responsibilities
- Clear separation of extraction, transformation, and loading
- Interface-based design allows for easy extension
- Strategy pattern makes business rules configurable
- Dependency injection enables testing
- Maintains 100% functional compatibility

## Reflection on AI Usage

### What Worked Well
- **Design Pattern Recognition:** AI quickly identified appropriate patterns (Strategy, Dependency Injection)
- **Code Organization:** AI suggested logical groupings and class hierarchies
- **Best Practices:** AI incorporated proper encapsulation, validation, and documentation
- **Error Correction:** AI immediately recognized and fixed the misplaced main method

### What Required Human Judgment
- **Business Logic Validation:** Ensured all original transformations were preserved
- **File Structure Decisions:** Chose appropriate package organization
- **Error Handling:** Maintained original error messages and user guidance
- **Performance Considerations:** Verified no unnecessary object creation

### Lessons Learned
1. **AI as Design Partner:** AI is excellent for brainstorming OOP patterns and suggesting improvements
2. **Iterative Refinement:** Multiple prompts helped clarify and improve the design
3. **Code Organization:** AI helped identify proper class boundaries and responsibilities
4. **Validation Importance:** Always verify that AI suggestions maintain original functionality

## Conclusion

Using AI as a design assistant significantly accelerated the object-oriented redesign process. The AI provided structured suggestions for applying OOP principles while I maintained control over business logic and requirements compliance. The result is a much more maintainable and extensible codebase that preserves all original functionality while being properly object-oriented.

The collaborative approach of AI suggesting patterns and improvements while I validated and adapted them proved highly effective for learning and applying object-oriented design principles.