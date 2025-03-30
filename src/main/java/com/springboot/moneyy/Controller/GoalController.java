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

    // ✅ Create a new goal with userId
    @PostMapping("/add/{userId}")
    public Goal createGoal(@PathVariable Long userId, @RequestBody Goal goal) {
        return goalService.createGoal(userId, goal);
    }

    // ✅ Get all goals
    @GetMapping
    public List<Goal> getAllGoals() {
        return goalService.getAllGoals();
    }

    // ✅ Get a goal by ID
    @GetMapping("/{id}")
    public Goal getGoalById(@PathVariable Long id) {
        return goalService.getGoalById(id);
    }

    @PutMapping("/update/{id}/{userId}")
    public Goal updateGoal(@PathVariable Long id, @PathVariable Long userId, @RequestBody Goal goal) {
        return goalService.updateGoal(id, userId, goal);
    }
    
    

    // ✅ Get goals sorted by target amount (descending)
    @GetMapping("/sorted/desc")
    public List<Goal> getGoalsSortedByTargetAmountDesc() {
        return goalService.getAllGoalsSortedByTargetAmountDesc();
    }

    // ✅ Delete a goal by ID
    @DeleteMapping("/delete/{id}")
    public void deleteGoal(@PathVariable Long id) {
        goalService.deleteGoal(id);
    }
}
