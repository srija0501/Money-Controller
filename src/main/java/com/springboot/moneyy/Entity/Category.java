package com.springboot.moneyy.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "category")  // Ensures the table name is 'category' in the database
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment ID
    private Long id;

    @Column(nullable = false, unique = true)  // Name must be unique and not null
    private String name;

    // Default constructor (required by JPA)
    public Category() {
    }

    // Constructor with parameters
    public Category(String name) {
        this.name = name;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
