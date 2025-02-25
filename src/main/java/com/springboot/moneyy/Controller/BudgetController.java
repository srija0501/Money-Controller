package com.springboot.moneyy.Controller;

import com.springboot.moneyy.Entity.Budget;
import com.springboot.moneyy.Service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

import java.util.List;

@RestController
@RequestMapping("/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    //  Get all budgets (list)
    @GetMapping()
    public List<Budget> getAllBudgets() {
        return budgetService.getAllBudgets();
    }

    //  Get budgets with Pagination & Sorting
    @GetMapping("/pagesort")
    public Page<Budget> getAllBudgetsPaginated(
            @PageableDefault(size = 5, sort = "amount", direction = Sort.Direction.ASC) Pageable pageable) {
        return budgetService.getAllBudgets(pageable);
    }

    //  Get budgets by category
    @GetMapping("/category/{category}")
    public List<Budget> getBudgetsByCategory(@PathVariable String category) {
        return budgetService.getBudgetsByCategory(category);
    }

    //  Create a new budget
    @PostMapping("/add")
    public Budget createBudget(@RequestBody Budget budget) {
        return budgetService.createBudget(budget);
    }

    //  Update budget
    @PutMapping("/update/{id}")
    public Budget updateBudget(@PathVariable Long id, @RequestBody Budget budget) {
        return budgetService.updateBudget(id, budget);
    }

    //  Get budgets greater than or equal to a certain amount
    @GetMapping("/amount/{amount}")
    public List<Budget> getBudgetsByAmount(@PathVariable Double amount) {
        return budgetService.getBudgetsByAmount(amount);
    }

    //  Delete budget
    @DeleteMapping("/{id}")
    public String deleteBudget(@PathVariable Long id) {
        budgetService.deleteBudget(id);
        return "Budget deleted successfully!";
    }
}
