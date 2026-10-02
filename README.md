# Smart Personal Finance Manager

A console-based personal finance management application developed using **Java, JDBC, MySQL, and Maven**. The application allows users to manage income and expense transactions and view their overall financial summary.

## Features

- Add income and expense transactions
- View all transactions
- Update existing transactions
- Delete transactions
- Search transactions by type, category, or description
- Calculate total income
- Calculate total expenses
- Display available balance
- Input validation for amounts and transaction types
- MySQL database integration using JDBC

## Technologies Used

- **Java 17**
- **JDBC**
- **MySQL**
- **Maven**
- **IntelliJ IDEA**
- **Git & GitHub**

## Project Structure

```text
SmartPersonalFinanceManager
│
├── pom.xml
├── .gitignore
│
└── src
    └── com
        └── finance
            ├── Main.java
            │
            ├── dao
            │   └── TransactionDAO.java
            │
            ├── model
            │   └── Transaction.java
            │
            └── util
                └── DatabaseConnection.java
```

## Application Menu

```text
========================================
     SMART PERSONAL FINANCE MANAGER
========================================

1. Add Transaction
2. View Transactions
3. Delete Transaction
4. Update Transaction
5. Search Transaction
6. Financial Summary
7. Exit
```

## Database

The application uses **MySQL** to store transaction data.

The `transactions` table contains:

- `id`
- `amount`
- `type`
- `category`
- `description`

The application connects to MySQL using **JDBC** and performs database operations using `PreparedStatement`.

## Architecture

The project follows a simple **Model–DAO–Utility architecture**.

### Model

`Transaction.java`

Represents a financial transaction and contains the transaction data.

### DAO

`TransactionDAO.java`

Handles database operations such as:

- Insert
- Select
- Update
- Delete
- Search
- Financial summary

### Utility

`DatabaseConnection.java`

Handles the connection between the Java application and MySQL database.

### Main

`Main.java`

Provides the console-based user interface and handles user input.

## How to Run

### 1. Clone the Repository

```bash
git clone https://github.com/2400033365Meghana23/SmartPersonalFinanceManager.git
```

### 2. Open the Project

Open the project in **IntelliJ IDEA** as a Maven project.

### 3. Configure MySQL

Create the required database and `transactions` table in MySQL.

### 4. Configure Database Password

The application reads the MySQL password from the environment variable:

```text
DB_PASSWORD
```

Set your MySQL password in the environment variable before running the application.

### 5. Run the Application

Run:

```text
src/com/finance/Main.java
```

The application will display the finance management menu in the console.

## Example Financial Summary

```text
========== FINANCIAL SUMMARY ==========

Total Income  : 1500.0
Total Expense : 1050.0
Balance       : 450.0
```

## Future Enhancements

- Monthly and yearly financial reports
- Budget management
- Category-wise expense analysis
- Export transactions to CSV
- Graphical user interface
- Login and user authentication
- Spending charts and visual reports

## Author

**Lalam Meghana**

Computer Science & Engineering Student
