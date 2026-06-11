# Bad Store Refactoring Project

## Overview

This project is a refactored version of the original **Bad Store** application. The goal of this was to improve the design while preserving the original functionality.

The refactoring focused on:

- Encapsulation
- Constructor validation and invariants
- Composition over inheritance
- Separation of concerns
- Reduced coupling
- Improved maintainability

## Major Design Improvements

### Encapsulation
- Replaced public mutable fields with private fields and getters.
- Added controlled methods for modifying object state.

### Validation
- Added constructor validation to prevent invalid objects.
- Enforced business rules such as non-negative prices, quantities, and loyalty points.

### Composition over Inheritance
- Replaced the incorrect inheritance relationship between `Order` and `Customer`.
- `Order` now contains a `Customer` and a `Product`.

### Enums
- Replaced string-based values with:
  - `CustomerType`
  - `OrderStatus`
  - `ShippingMethod`

### Separation of Concerns

Responsibilities were separated into dedicated classes:

- `StoreService` – store operations
- `LoyaltyProgram` – loyalty point management
- `ShippingCalculator` – shipping calculations
- `StoreLogger` – logging
- `NotificationService` – order confirmations
- `SalesReportService` – reporting

## Running the Program

Run:

```text
StoreAppMain.java
```

Available commands:

```text
LIST_PRODUCTS
LIST_CUSTOMERS
ORDER
REPORT
EXIT
```

Example:

```text
ORDER
alice@example.com
P1
1
```

## UML Diagram

The UML diagram included with the submission reflects the final class relationships and design.

## Author

Erick Somentac
