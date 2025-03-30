package com.springboot.moneyy.Service;

import com.springboot.moneyy.Entity.Income;
import com.springboot.moneyy.Entity.User;
import com.springboot.moneyy.Repository.IncomeRepository;
import com.springboot.moneyy.Repository.UserRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private UserRepository userRepository;

    // Fetch all incomes
    public List<Income> getAllIncomes() {
        return incomeRepository.findAll();
    }

    // Fetch a single income by ID
    public Optional<Income> getIncomeById(Long id) {
        return incomeRepository.findById(id);
    }

    // Fetch incomes by category
    public List<Income> getIncomesByCategory(String category) {
        return incomeRepository.findByCategory(category);
    }

    // Add a new income and associate it with a User
    public Income addIncome(Long userId, Income income) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            income.setUser(user);  // Link income to the user
            user.getIncomes().add(income); // Ensure bidirectional relationship
            userRepository.save(user); // Save user to ensure persistence
            return incomeRepository.save(income);
        }
        throw new RuntimeException("User not found with ID: " + userId);
    }

    // Update an existing income and ensure it remains linked to the User
    public Income updateIncome(Long id, Long userId, Income updatedIncome) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Income> existingIncomeOptional = incomeRepository.findById(id);

        if (userOptional.isPresent() && existingIncomeOptional.isPresent()) {
            User user = userOptional.get();
            Income existingIncome = existingIncomeOptional.get();

            existingIncome.setCategory(updatedIncome.getCategory());
            existingIncome.setAmount(updatedIncome.getAmount());
            existingIncome.setDate(updatedIncome.getDate());

            return incomeRepository.save(existingIncome);
        }
        throw new RuntimeException("User or Income not found.");
    }

    // Delete an income and remove it from the user
    @Transactional
    public void deleteIncome(Long id) {
        Income income = incomeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Income not found with ID: " + id));

        User user = income.getUser();
        if (user != null) {
            user.getIncomes().remove(income); // Remove from User's list
            userRepository.save(user); // Save User before deleting income
        }

        incomeRepository.delete(income);
    }
    
}
