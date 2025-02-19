package com.springboot.moneyy.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary Key

    private Double amount;  // Transaction Amount

    private LocalDate date;  // Transaction Date

    private String description;  // Transaction Description

    private String category;  // New Category Field

    private Long userId;  // Storing user ID directly (No relationship)

    public Transaction() {}

    public Transaction(Double amount, LocalDate date, String description, String category, Long userId) {
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.category = category;
        this.userId = userId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
