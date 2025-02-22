package com.springboot.moneyy.Controller;

import com.springboot.moneyy.Entity.Budget;
import com.springboot.moneyy.Service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    
    @GetMapping
    public List<Budget> getAllBudgets() {
        return budgetService.getAllBudgets();
    }

   
    @PostMapping("/add")
    public Budget createBudget(@RequestBody Budget budget) {
        return budgetService.createBudget(budget);
    }


    @PutMapping("/update/{id}")
public Budget updateBudget(@PathVariable Long id, @RequestBody Budget budget) {
    return budgetService.updateBudget(id, budget);
}

         @GetMapping("/amount/{amount}")
          public List<Budget> getBudgetsByAmount(@PathVariable Double amount) {
                     return budgetService.getBudgetsByAmount(amount);
}



    // Delete a budget
    @DeleteMapping("/{id}")
    public String deleteBudget(@PathVariable Long id) {
        budgetService.deleteBudget(id);
        return "Budget deleted successfully!";
    }
}
