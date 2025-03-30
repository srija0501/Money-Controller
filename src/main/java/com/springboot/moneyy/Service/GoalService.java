package com.springboot.moneyy.Service;

import com.springboot.moneyy.Entity.Goal;
import com.springboot.moneyy.Entity.User;
import com.springboot.moneyy.Repository.GoalRepository;
import com.springboot.moneyy.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;
    
    @Autowired
    private UserRepository userRepository; // ✅ Ensure this is injected properly

  

    public Goal createGoal(Long userId, Goal goal) {
        // Find user by ID, throw error if not found
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        goal.setUser(user); // ✅ Associate the goal with the user

        return goalRepository.save(goal);
    }
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

   
  


    public Goal updateGoal(Long id, Long userId, Goal goal) {
        if (!goalRepository.existsById(id)) {
            throw new RuntimeException("Goal not found with ID: " + id);
        }

        // ✅ Use the instance of `userRepository`, not the class name
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        goal.setId(id);
        goal.setUser(user); // ✅ Associate goal with user

        return goalRepository.save(goal);
    }
    

    public List<Goal> getAllGoalsSortedByTargetAmountDesc() {
        return goalRepository.findAllByOrderByTargetAmountDesc();
    }

    public void deleteGoal(Long id) {
        if (!goalRepository.existsById(id)) {
            throw new RuntimeException("Goal not found with ID: " + id);
        }
        goalRepository.deleteById(id);
    }
}
