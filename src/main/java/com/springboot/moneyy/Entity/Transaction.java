
package com.springboot.moneyy.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  

    private Double amount;  

    private LocalDate date;  

    private String description; 

    

    private Long userId;  

    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="category_id")
    @JsonBackReference
   
    private Category category;

    

    public Transaction() {}

    public Transaction(Double amount, LocalDate date, String description, Long userId) {
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.userId = userId;
    }

    public Category getCategory() {
        return category;
    }

    public void  setCategory (Category category) {
        this.category = category;
    }
  
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


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
