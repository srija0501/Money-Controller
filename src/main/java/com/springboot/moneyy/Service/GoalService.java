package com.springboot.moneyy.Service;

import com.springboot.moneyy.Entity.Goal;
import com.springboot.moneyy.Repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;

    // Create a new goal
    public Goal createGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    // Get all goals
    public List<Goal> getAllGoals() {
        return goalRepository.findAll();
    }

    // Get goal by ID
    public Goal getGoalById(Long id) {
        Optional<Goal> goal = goalRepository.findById(id);
        if (goal.isPresent()) {
            return goal.get();
        } else {
            throw new RuntimeException("Goal not found with ID: " + id);
        }
    }

    // Update an existing goal
    public Goal updateGoal(Long id, Goal goal) {
        // Check if goal exists
        if (!goalRepository.existsById(id)) {
            throw new RuntimeException("Goal not found with ID: " + id);
        }

        goal.setId(id); // Set the ID to preserve the goal's identity
        return goalRepository.save(goal);
    }
    public List<Goal> getAllGoalsSortedByTargetAmountDesc() {
        return goalRepository.findAllByOrderByTargetAmountDesc();
    }

    // Delete goal by ID
    public void deleteGoal(Long id) {
        if (!goalRepository.existsById(id)) {
            throw new RuntimeException("Goal not found with ID: " + id);
        }
        goalRepository.deleteById(id);
    }
}
