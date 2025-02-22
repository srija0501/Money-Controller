package com.springboot.moneyy.Controller;

import com.springboot.moneyy.Entity.Goal;
import com.springboot.moneyy.Service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goal")
public class GoalController {

    @Autowired
    private GoalService goalService;

    // Create a new goal
    @PostMapping("/adds")
    public Goal createGoal(@RequestBody Goal goal) {
        return goalService.createGoal(goal);
    }

    // Get all goals
    @GetMapping
    public List<Goal> getAllGoals() {
        return goalService.getAllGoals();
    }

    // Get a goal by ID
    @GetMapping("/{id}")
    public Goal getGoalById(@PathVariable Long id) {
        return goalService.getGoalById(id);
    }

    // Update an existing goal
    @PutMapping("/update/{id}")
    public Goal updateGoal(@PathVariable Long id, @RequestBody Goal goal) {
        return goalService.updateGoal(id, goal);
    }
    @GetMapping("/sorted/desc")
    public List<Goal> getGoalsSortedByTargetAmountDesc() {
        return goalService.getAllGoalsSortedByTargetAmountDesc();
    }

    // Delete a goal by ID
    @DeleteMapping("/delete/{id}")
    public void deleteGoal(@PathVariable Long id) {
        goalService.deleteGoal(id);
    }
}
