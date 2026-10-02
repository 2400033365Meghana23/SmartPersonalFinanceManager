package com.finance.model;

public class Transaction {

    private double amount;
    private String type;
    private String category;
    private String description;

    public Transaction(double amount, String type,
                       String category, String description) {

        this.amount = amount;
        this.type = type;
        this.category = category;
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}