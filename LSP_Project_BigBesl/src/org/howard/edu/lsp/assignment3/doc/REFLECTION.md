# Reflection: ETL Pipeline Evolution from Assignment 2 to Assignment 3

**Student:** Bryce Ly  
**Course:** CSCI 363  
**Assignment:** Object-Oriented Redesign Reflection  
**Date:** September 2025

## Executive Summary

This reflection compares my original ETL pipeline from Assignment 2 with the object-oriented redesign completed in Assignment 3. The transformation from a single monolithic class to a 12-class object-oriented system demonstrates the practical application of OOP principles and highlights both the benefits and complexities of proper software design.

## Architecture Comparison

### Assignment 2: Monolithic Design
- **Single Class:** `ETLPipeline` containing all functionality
- **Mixed Responsibilities:** File I/O, data transformation, business logic, and error handling all in one place
- **Procedural Approach:** Methods called sequentially within the main class
- **Static Inner Class:** `Product` as a simple data container with minimal encapsulation

### Assignment 3: Object-Oriented Design
- **12 Separate Classes:** Each with a single, well-defined responsibility
- **Layered Architecture:** Clear separation between extraction, transformation, and loading layers
- **Interface-Based Design:** Contracts defined for major operations
- **Strategy Pattern:** Pluggable business rules for pricing and categorization

## Detailed Comparison

### Code Organization

**Assignment 2 Structure:**
```
ETLPipeline.java (everything in one file)
├── main()
├── run()
├── extractData()
├── readFromFile()
├── transformData()
├── transformProduct()
├── loadData()
├── printSummary()
└── static class Product
```

**Assignment 3 Structure:**
```
12 separate files organized by layer:

Data Model:
├── Product.java

Extraction Layer:
├── DataExtractor.java (interface)
└── CSVDataExtractor.java (implementation)

Transformation Layer:
├── DataTransformer.java (interface)
├── ProductDataTransformer.java (coordinator)
├── PricingStrategy.java (interface)
├── StandardPricingStrategy.java (implementation)
├── CategoryManager.java (interface)
└── StandardCategoryManager.java (implementation)

Loading Layer:
├── DataLoader.java (interface)
└── CSVDataLoader.java (implementation)

Orchestration:
└── ETLPipeline.java (main coordinator)
```

### Object-Oriented Principles Applied

#### 1. **Encapsulation**

**Assignment 2:**
- Product fields were package-private
- Direct field access throughout the code
- No validation on data modification

**Assignment 3:**
- All Product fields are private
- Controlled access through getters/setters
- Validation in setters (non-negative prices, null checks)
- Data integrity protection

**Impact:** Better data protection and more predictable behavior.

#### 2. **Single Responsibility Principle**

**Assignment 2:**
- `ETLPipeline` class responsible for:
  - File validation and reading
  - CSV parsing and error handling
  - All business logic transformations
  - File writing and output formatting
  - Error reporting and user guidance

**Assignment 3:**
- Each class has one clear responsibility:
  - `CSVDataExtractor`: Only handles CSV file reading
  - `StandardPricingStrategy`: Only applies pricing rules
  - `StandardCategoryManager`: Only handles categorization
  - `CSVDataLoader`: Only writes CSV output
  - `ETLPipeline`: Only orchestrates the process flow

**Impact:** Easier to understand, test, and maintain individual components.

#### 3. **Interface Segregation & Polymorphism**

**Assignment 2:**
- No interfaces
- Direct method calls
- Tight coupling between operations

**Assignment 3:**
- Three main interfaces: `DataExtractor`, `DataTransformer`, `DataLoader`
- Two strategy interfaces: `PricingStrategy`, `CategoryManager`
- Polymorphic behavior allows runtime strategy swapping

**Impact:** Flexible design that supports multiple implementations and easy extension.

#### 4. **Dependency Injection**

**Assignment 2:**
- Hard-coded dependencies
- Direct instantiation of all components
- Impossible to substitute implementations for testing

**Assignment 3:**
- Constructor injection in `ETLPipeline`
- Default implementations provided
- Easy to inject mock objects for testing

**Impact:** Highly testable and configurable system.

## Functional Equivalence Analysis

### What Remained the Same
- **Input/Output Behavior:** Exact same file paths, formats, and transformations
- **Error Handling:** All original error messages and user guidance preserved
- **Business Rules:** Identical transformation logic (name uppercase, electronics discount, premium categorization, price ranges)
- **Performance:** No significant performance impact from the redesign

### What Improved
- **Code Readability:** Clear class names and focused responsibilities
- **Maintainability:** Changes to business rules now isolated to specific classes
- **Testability:** Individual components can be tested in isolation
- **Extensibility:** Easy to add new file formats, transformation rules, or output destinations

## Benefits of the Object-Oriented Approach

### 1. **Maintainability**
- **Before:** Changing discount percentage required editing within the main transformation method
- **After:** Simply modify `StandardPricingStrategy.ELECTRONICS_DISCOUNT` constant
- **Benefit:** Changes are localized and don't risk affecting other functionality

### 2. **Extensibility**
- **Before:** Adding JSON support would require major modifications to the main class
- **After:** Simply implement `DataExtractor` and `DataLoader` interfaces for JSON
- **Benefit:** Open/Closed Principle - open for extension, closed for modification

### 3. **Testability**
- **Before:** Testing required creating actual files and running the entire pipeline
- **After:** Can test individual strategies and components with mock objects
- **Benefit:** Faster, more targeted testing with better error isolation

### 4. **Reusability**
- **Before:** Business logic embedded in the ETL process
- **After:** `StandardPricingStrategy` could be reused in other contexts
- **Benefit:** Components can be shared across different applications

## Trade-offs and Challenges

### Increased Complexity
- **Assignment 2:** 1 file, ~200 lines, easy to understand at a glance
- **Assignment 3:** 12 files, ~400+ lines, requires understanding of multiple classes and relationships
- **Impact:** Higher learning curve for new developers

### More Coordination Required
- **Assignment 2:** Direct method calls, simple flow
- **Assignment 3:** Interface contracts, dependency management
- **Impact:** More design decisions and potential points of failure

### Potential Over-Engineering
- **Risk:** For a simple ETL pipeline, the OOP approach might seem excessive
- **Reality:** The current design can now easily handle multiple file formats, complex business rules, and different output destinations

## Learning Outcomes

### Technical Skills Developed
1. **Interface Design:** Creating meaningful contracts between components
2. **Strategy Pattern:** Implementing pluggable business rules
3. **Dependency Injection:** Designing for testability and flexibility
4. **Class Decomposition:** Breaking monolithic code into focused components

### Design Thinking Evolution
- **Before:** Procedural thinking - "What steps need to happen?"
- **After:** Object-oriented thinking - "What responsibilities exist and how do they interact?"

### Code Quality Awareness
- Understanding the importance of separation of concerns
- Recognizing when and why to apply OOP principles
- Balancing simplicity with extensibility

## Future Enhancements Enabled

The object-oriented design now makes the following enhancements straightforward:

1. **Multiple File Formats:** Add `JSONDataExtractor`, `XMLDataLoader`
2. **Complex Business Rules:** Create `SeasonalPricingStrategy`, `BulkDiscountStrategy`
3. **Data Validation:** Add `DataValidator` interface with various validation strategies
4. **Logging and Monitoring:** Inject logging strategies into each component
5. **Configuration Management:** External configuration for business rules and file paths

## Conclusion

The transformation from Assignment 2 to Assignment 3 demonstrates the power of object-oriented design principles. While the monolithic approach was simpler and more direct, the object-oriented redesign provides significant benefits in maintainability, extensibility, and testability.

The key insight is that OOP isn't about making code more complex - it's about managing complexity better. By breaking the system into focused, well-defined components, we create a foundation that can grow and adapt to changing requirements without requiring major rewrites.

This exercise reinforced that good software design is about making future changes easier, not just making current requirements work. The initial investment in proper design pays dividends when modifications and extensions are needed.

### Personal Reflection

This redesign challenged me to think beyond "does it work?" to "how well is it designed?" The process of identifying responsibilities, creating appropriate abstractions, and maintaining functional equivalence while improving structure was both challenging and rewarding. It demonstrated that there's often a significant difference between code that works and code that's well-designed.

The AI assistance was valuable in suggesting patterns and identifying opportunities for improvement, but the critical thinking about business requirements, trade-offs, and implementation details remained firmly in human hands. This collaborative approach to software design feels like a preview of how development teams will work with AI tools in the future.