package com.springboot.moneyy.Controller;

import com.springboot.moneyy.Entity.Income;
import com.springboot.moneyy.Service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/income")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @GetMapping
    public List<Income> getAllIncomes() {
        return incomeService.getAllIncomes();
    }

    @GetMapping("/{id}")
    public Optional<Income> getIncomeById(@PathVariable Long id) {
        return incomeService.getIncomeById(id);
    }

    @GetMapping("/category/{category}")
    public List<Income> getIncomesByCategory(@PathVariable String category) {
        return incomeService.getIncomesByCategory(category);
    }

    // Add income with user association
    @PostMapping("/add/{userId}")
    public Income addIncome(@PathVariable Long userId, @RequestBody Income income) {
        return incomeService.addIncome(userId, income);
    }

    // Update income with user association
    @PutMapping("/update/{id}/{userId}")
    public Income updateIncome(@PathVariable Long id, @PathVariable Long userId, @RequestBody Income incomeDetails) {
        return incomeService.updateIncome(id, userId, incomeDetails);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteIncome(@PathVariable Long id) {
        incomeService.deleteIncome(id);
        return "Income deleted successfully!";
    }
}
