package com.springboot.moneyy.Service;

import com.springboot.moneyy.Entity.Income;
import com.springboot.moneyy.Repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    // Get all incomes
    public List<Income> getAllIncomes() {
        return incomeRepository.findAll();
    }

    // Get income by ID
    public Optional<Income> getIncomeById(Long id) {
        return incomeRepository.findById(id);
    }

    // Get incomes by category
    public List<Income> getIncomesByCategory(String category) {
        return incomeRepository.findByCategory(category);
    }

    // Add new income
    public Income addIncome(Income income) {
        return incomeRepository.save(income);
    }

    // Update income
    public Income updateIncome(Long id, Income incomeDetails) {
        return incomeRepository.findById(id).map(income -> {
            income.setAmount(incomeDetails.getAmount());
            income.setDate(incomeDetails.getDate());
            income.setCategory(incomeDetails.getCategory());
            return incomeRepository.save(income);
        }).orElseThrow(() -> new RuntimeException("Income not found with ID: " + id));
    }

    public void deleteIncome(Long id) {
        incomeRepository.deleteById(id);
    }
}
