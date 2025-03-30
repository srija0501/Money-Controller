package com.springboot.moneyy.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
public class Expense {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private LocalDate date;
   
    private Long userId;

   
    public Expense() {}

    public Expense( Double amount, LocalDate date, Long userId) {
      
        this.amount = amount;
        this.date = date;
       
        this.userId = userId;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

   

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}

