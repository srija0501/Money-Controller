package com.springboot.moneyy.Service;

import com.springboot.moneyy.Entity.Budget;
import com.springboot.moneyy.Repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    // Get all budgets
    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    // Create a new budget
    public Budget createBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    // Update an existing budget
    public Budget updateBudget(Long id, Budget updatedBudget) {
        Budget existingBudget = budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget not found"));
        
        existingBudget.setAmount(updatedBudget.getAmount());
        existingBudget.setStartDate(updatedBudget.getStartDate());
        existingBudget.setEndDate(updatedBudget.getEndDate());

        return budgetRepository.save(existingBudget);
    }

    // Delete a budget
    public void deleteBudget(Long id) {
        budgetRepository.deleteById(id);
    }
}
