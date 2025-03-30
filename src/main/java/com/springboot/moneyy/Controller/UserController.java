package com.springboot.moneyy.Controller;

import com.springboot.moneyy.Entity.User;
import com.springboot.moneyy.Entity.Income;
import com.springboot.moneyy.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ Get all users (list)
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // ✅ Get user by ID
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // ✅ Get paginated users
    @GetMapping("/page")
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        return userService.getAllUsers(page, size);
    }

    // ✅ Create a new user
    @PostMapping("/add")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // ✅ Update user by ID
    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    // ✅ Delete user by ID
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted successfully!";
    }

    // ✅ Get all incomes of a user
    @GetMapping("/{userId}/incomes")
    public List<Income> getUserIncomes(@PathVariable Long userId) {
        return userService.getUserIncomes(userId);
    }

    // ✅ Add income to a user
    @PostMapping("/{userId}/incomes")
    public Income addIncomeToUser(@PathVariable Long userId, @RequestBody Income income) {
        return userService.addIncomeToUser(userId, income);
    }
}
