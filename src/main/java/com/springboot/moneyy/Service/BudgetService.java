package com.springboot.moneyy.Service;

import com.springboot.moneyy.Entity.Budget;
import com.springboot.moneyy.Repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public List<Budget> getBudgetsByAmount(Double amount) {
        return budgetRepository.findBudgetsByAmountGreaterThanEqual(amount);
    }
    

    public Budget createBudget(Budget budget) {
        return budgetRepository.save(budget);
    }
   
    public Budget updateBudget(Long id, Budget updatedBudget) {
             updatedBudget.setId(id);
             return budgetRepository.save(updatedBudget);
    }

    public Page<Budget> getAllBudgets(Pageable pageable) {
        return budgetRepository.findAll(pageable);
    }

    public List<Budget> getBudgetsByCategory(String category) {
        return budgetRepository.findByCategory(category);
    }

    
    

    public void deleteBudget(Long id) {
        budgetRepository.deleteById(id);
    }
}
