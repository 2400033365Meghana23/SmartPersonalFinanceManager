package com.finance.dao;

import com.finance.model.Transaction;
import com.finance.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDAO {

    // ==========================================
    // ADD TRANSACTION
    // ==========================================
    public void addTransaction(Transaction transaction) {

        String sql = "INSERT INTO transactions " +
                "(amount, type, category, description) " +
                "VALUES (?, ?, ?, ?)";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setDouble(
                    1,
                    transaction.getAmount()
            );

            statement.setString(
                    2,
                    transaction.getType()
            );

            statement.setString(
                    3,
                    transaction.getCategory()
            );

            statement.setString(
                    4,
                    transaction.getDescription()
            );

            statement.executeUpdate();

            System.out.println(
                    "Transaction added successfully!"
            );

        } catch (SQLException e) {

            System.out.println(
                    "Failed to add transaction."
            );

            e.printStackTrace();
        }
    }


    // ==========================================
    // VIEW ALL TRANSACTIONS
    // ==========================================
    public void getAllTransactions() {

        String sql = "SELECT * FROM transactions";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            System.out.println(
                    "\n========== ALL TRANSACTIONS =========="
            );

            boolean found = false;

            while (resultSet.next()) {

                found = true;

                int id =
                        resultSet.getInt("id");

                double amount =
                        resultSet.getDouble("amount");

                String type =
                        resultSet.getString("type");

                String category =
                        resultSet.getString("category");

                String description =
                        resultSet.getString("description");

                System.out.println(
                        id +
                                " | Amount: " + amount +
                                " | Type: " + type +
                                " | Category: " + category +
                                " | Description: " + description
                );
            }

            if (!found) {

                System.out.println(
                        "No transactions found."
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Failed to retrieve transactions."
            );

            e.printStackTrace();
        }
    }


    // ==========================================
    // CHECK TRANSACTION EXISTS
    // ==========================================
    public boolean transactionExists(int id) {

        String sql =
                "SELECT id FROM transactions WHERE id = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            try (
                    ResultSet resultSet =
                            statement.executeQuery()
            ) {

                return resultSet.next();
            }

        } catch (SQLException e) {

            System.out.println(
                    "Failed to check transaction."
            );

            e.printStackTrace();

            return false;
        }
    }


    // ==========================================
    // UPDATE TRANSACTION
    // ==========================================
    public void updateTransaction(
            Transaction transaction,
            int id
    ) {

        String sql =
                "UPDATE transactions " +
                        "SET amount = ?, type = ?, " +
                        "category = ?, description = ? " +
                        "WHERE id = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setDouble(
                    1,
                    transaction.getAmount()
            );

            statement.setString(
                    2,
                    transaction.getType()
            );

            statement.setString(
                    3,
                    transaction.getCategory()
            );

            statement.setString(
                    4,
                    transaction.getDescription()
            );

            statement.setInt(5, id);

            int rowsUpdated =
                    statement.executeUpdate();

            if (rowsUpdated > 0) {

                System.out.println(
                        "Transaction updated successfully!"
                );

            } else {

                System.out.println(
                        "Transaction ID not found."
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Failed to update transaction."
            );

            e.printStackTrace();
        }
    }


    // ==========================================
    // DELETE TRANSACTION
    // ==========================================
    public void deleteTransaction(int id) {

        String sql =
                "DELETE FROM transactions WHERE id = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            int rowsDeleted =
                    statement.executeUpdate();

            if (rowsDeleted > 0) {

                System.out.println(
                        "Transaction deleted successfully!"
                );

            } else {

                System.out.println(
                        "Transaction ID not found."
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Failed to delete transaction."
            );

            e.printStackTrace();
        }
    }


    // ==========================================
    // SEARCH TRANSACTIONS
    // ==========================================
    public void searchTransactions(String keyword) {

        String sql =
                "SELECT * FROM transactions " +
                        "WHERE type LIKE ? " +
                        "OR category LIKE ? " +
                        "OR description LIKE ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            String searchKeyword =
                    "%" + keyword + "%";

            statement.setString(
                    1,
                    searchKeyword
            );

            statement.setString(
                    2,
                    searchKeyword
            );

            statement.setString(
                    3,
                    searchKeyword
            );

            try (
                    ResultSet resultSet =
                            statement.executeQuery()
            ) {

                System.out.println(
                        "\n========== SEARCH RESULTS =========="
                );

                boolean found = false;

                while (resultSet.next()) {

                    found = true;

                    int id =
                            resultSet.getInt("id");

                    double amount =
                            resultSet.getDouble("amount");

                    String type =
                            resultSet.getString("type");

                    String category =
                            resultSet.getString("category");

                    String description =
                            resultSet.getString("description");

                    System.out.println(
                            id +
                                    " | Amount: " + amount +
                                    " | Type: " + type +
                                    " | Category: " + category +
                                    " | Description: " + description
                    );
                }

                if (!found) {

                    System.out.println(
                            "No matching transactions found."
                    );
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Search failed."
            );

            e.printStackTrace();
        }
    }


    // ==========================================
    // FINANCIAL SUMMARY
    // ==========================================
    public void showFinancialSummary() {

        String sql =
                "SELECT " +
                        "SUM(CASE WHEN type = 'income' " +
                        "THEN amount ELSE 0 END) AS total_income, " +
                        "SUM(CASE WHEN type = 'expense' " +
                        "THEN amount ELSE 0 END) AS total_expense " +
                        "FROM transactions";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            if (resultSet.next()) {

                double totalIncome =
                        resultSet.getDouble(
                                "total_income"
                        );

                double totalExpense =
                        resultSet.getDouble(
                                "total_expense"
                        );

                double balance =
                        totalIncome - totalExpense;

                System.out.println(
                        "\n========== FINANCIAL SUMMARY =========="
                );

                System.out.println(
                        "Total Income  : " +
                                totalIncome
                );

                System.out.println(
                        "Total Expense : " +
                                totalExpense
                );

                System.out.println(
                        "Balance       : " +
                                balance
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Failed to calculate financial summary."
            );

            e.printStackTrace();
        }
    }
}