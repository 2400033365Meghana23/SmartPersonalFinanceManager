package com.finance;

import com.finance.dao.TransactionDAO;
import com.finance.model.Transaction;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static TransactionDAO dao = new TransactionDAO();

    public static void main(String[] args) {

        boolean running = true;

        while (running) {

            displayMenu();

            int choice =
                    readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    addTransaction();
                    break;

                case 2:
                    dao.getAllTransactions();
                    break;

                case 3:
                    deleteTransaction();
                    break;

                case 4:
                    updateTransaction();
                    break;

                case 5:
                    searchTransaction();
                    break;

                case 6:
                    dao.showFinancialSummary();
                    break;

                case 7:

                    running = false;

                    System.out.println(
                            "\nThank you for using " +
                                    "Smart Personal Finance Manager!"
                    );

                    break;

                default:

                    System.out.println(
                            "Invalid choice. " +
                                    "Please enter a number from 1 to 7."
                    );
            }
        }

        sc.close();
    }


    // ==========================================
    // DISPLAY MENU
    // ==========================================
    static void displayMenu() {

        System.out.println();

        System.out.println(
                "========================================"
        );

        System.out.println(
                "     SMART PERSONAL FINANCE MANAGER"
        );

        System.out.println(
                "========================================"
        );

        System.out.println("1. Add Transaction");
        System.out.println("2. View Transactions");
        System.out.println("3. Delete Transaction");
        System.out.println("4. Update Transaction");
        System.out.println("5. Search Transaction");
        System.out.println("6. Financial Summary");
        System.out.println("7. Exit");
    }


    // ==========================================
    // ADD TRANSACTION
    // ==========================================
    static void addTransaction() {

        System.out.println(
                "\n========== ADD TRANSACTION =========="
        );

        double amount;

        while (true) {

            amount =
                    readDouble("Enter Amount: ");

            if (amount > 0) {
                break;
            }

            System.out.println(
                    "Amount must be greater than 0."
            );
        }


        String type;

        while (true) {

            System.out.print(
                    "Enter Type (income/expense): "
            );

            type =
                    sc.nextLine()
                            .trim()
                            .toLowerCase();

            if (type.equals("income") ||
                    type.equals("expense")) {

                break;
            }

            System.out.println(
                    "Invalid type. " +
                            "Please enter income or expense."
            );
        }


        System.out.print("Enter Category: ");

        String category =
                sc.nextLine().trim();


        System.out.print("Enter Description: ");

        String description =
                sc.nextLine().trim();


        Transaction transaction =
                new Transaction(
                        amount,
                        type,
                        category,
                        description
                );

        dao.addTransaction(transaction);
    }


    // ==========================================
    // DELETE TRANSACTION
    // ==========================================
    static void deleteTransaction() {

        dao.getAllTransactions();

        int id =
                readInt(
                        "\nEnter transaction ID to delete: "
                );

        if (id <= 0) {

            System.out.println(
                    "Transaction ID must be greater than 0."
            );

            return;
        }

        dao.deleteTransaction(id);
    }


    // ==========================================
    // UPDATE TRANSACTION
    // ==========================================
    static void updateTransaction() {

        dao.getAllTransactions();

        int id =
                readInt(
                        "\nEnter transaction ID to update: "
                );

        if (id <= 0) {

            System.out.println(
                    "Transaction ID must be greater than 0."
            );

            return;
        }


        // CHECK WHETHER ID EXISTS
        if (!dao.transactionExists(id)) {

            System.out.println(
                    "Transaction ID not found."
            );

            return;
        }


        double amount;

        while (true) {

            amount =
                    readDouble("Enter new Amount: ");

            if (amount > 0) {
                break;
            }

            System.out.println(
                    "Amount must be greater than 0."
            );
        }


        String type;

        while (true) {

            System.out.print(
                    "Enter new Type (income/expense): "
            );

            type =
                    sc.nextLine()
                            .trim()
                            .toLowerCase();

            if (type.equals("income") ||
                    type.equals("expense")) {

                break;
            }

            System.out.println(
                    "Invalid type. " +
                            "Please enter income or expense."
            );
        }


        System.out.print("Enter new Category: ");

        String category =
                sc.nextLine().trim();


        System.out.print("Enter new Description: ");

        String description =
                sc.nextLine().trim();


        Transaction transaction =
                new Transaction(
                        amount,
                        type,
                        category,
                        description
                );

        dao.updateTransaction(
                transaction,
                id
        );
    }


    // ==========================================
    // SEARCH TRANSACTION
    // ==========================================
    static void searchTransaction() {

        System.out.println(
                "\n========== SEARCH TRANSACTION =========="
        );

        System.out.print("Enter keyword: ");

        String keyword =
                sc.nextLine().trim();

        if (keyword.isEmpty()) {

            System.out.println(
                    "Search keyword cannot be empty."
            );

            return;
        }

        dao.searchTransactions(keyword);
    }


    // ==========================================
    // READ INTEGER SAFELY
    // ==========================================
    static int readInt(String message) {

        while (true) {

            System.out.print(message);

            String input =
                    sc.nextLine();

            try {

                return Integer.parseInt(
                        input.trim()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. " +
                                "Please enter a number."
                );
            }
        }
    }


    // ==========================================
    // READ DOUBLE SAFELY
    // ==========================================
    static double readDouble(String message) {

        while (true) {

            System.out.print(message);

            String input =
                    sc.nextLine();

            try {

                return Double.parseDouble(
                        input.trim()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid amount. " +
                                "Please enter a number."
                );
            }
        }
    }
}