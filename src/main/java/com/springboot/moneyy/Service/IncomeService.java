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

    
    public List<Income> getAllIncomes() {
        return incomeRepository.findAll();
    }


    public Optional<Income> getIncomeById(Long id) {
        return incomeRepository.findById(id);
    }

 
    public List<Income> getIncomesByCategory(String category) {
        return incomeRepository.findByCategory(category);
    }

   
    public Income addIncome(Income income) {
        return incomeRepository.save(income);
    }

    public Income updateIncome(Long id, Income updatedIncome) {
        updatedIncome.setId(id);
        return incomeRepository.save(updatedIncome);
    }
    

    public void deleteIncome(Long id) {
        incomeRepository.deleteById(id);
    }
}
