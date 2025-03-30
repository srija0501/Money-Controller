package com.springboot.moneyy.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; 

    private String name;
    private String email;
    
    @JsonIgnore
    private String password; 

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Income> incomes = new ArrayList<>();
    

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)  // Mapped by "user" in Goal
    @JsonManagedReference
    private List<Goal> goalslist = new ArrayList<>();

    public List<Goal> getGoalslist() {
        return goalslist;
    }

    public void setGoalslist(List<Goal> goalslist) {
        for (Goal goal : goalslist) {
            goal.setUser(this);
        }
        this.goalslist = goalslist;
    }

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Income> incomes) {
        for (Income income : incomes) {
            income.setUser(this);
        }
        this.incomes = incomes;
    }
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
