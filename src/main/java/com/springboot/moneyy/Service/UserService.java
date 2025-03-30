package com.springboot.moneyy.Service;

import com.springboot.moneyy.Entity.User;
import com.springboot.moneyy.Entity.Goal;
import com.springboot.moneyy.Entity.Income;
import com.springboot.moneyy.Repository.UserRepository;
import com.springboot.moneyy.Repository.IncomeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IncomeRepository incomeRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Page<User> getAllUsers(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        if (user.getIncomes() == null) {
            user.setIncomes(new ArrayList<>());
        }
        for (Income income : user.getIncomes()) {
            income.setUser(user);
        }

        if (user.getGoalslist() == null) {
            user.setGoalslist(new ArrayList<>());
        }
        for (Goal goal : user.getGoalslist()) {
            goal.setUser(user);
        }

        User savedUser = userRepository.save(user);
        incomeRepository.saveAll(savedUser.getIncomes());

        return savedUser;
    }

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
    
            // Handle incomes update
            if (updatedUser.getIncomes() != null) {
                for (Income income : updatedUser.getIncomes()) {
                    income.setUser(user);
                }
                incomeRepository.saveAll(updatedUser.getIncomes()); // Save incomes before setting
                user.getIncomes().clear();
                user.getIncomes().addAll(updatedUser.getIncomes());
            }
    
            // Handle goals update
            if (updatedUser.getGoalslist() != null) {
                for (Goal goal : updatedUser.getGoalslist()) {
                    goal.setUser(user);
                }
                user.getGoalslist().clear();
                user.getGoalslist().addAll(updatedUser.getGoalslist());
            }
    
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }
    

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        incomeRepository.deleteAll(user.getIncomes());
        user.getIncomes().clear();
        user.getGoalslist().clear();

        userRepository.delete(user);
    }

    public List<Income> getUserIncomes(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        return user.getIncomes();
    }

    public Income addIncomeToUser(Long userId, Income income) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        if (user.getIncomes() == null) {
            user.setIncomes(new ArrayList<>());
        }

        income.setUser(user);
        user.getIncomes().add(income);

        return incomeRepository.save(income);
    }
}
