package com.springboot.moneyy.Controller;

import com.springboot.moneyy.Entity.Transaction;
import com.springboot.moneyy.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Get all transactions
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    // Get transactions for a specific user
    @GetMapping("/user/{userId}")
    public List<Transaction> getTransactionsByUser(@PathVariable Long userId) {
        return transactionService.getTransactionsByUser(userId);
    }

    // Create a new transaction
    @PostMapping("/add")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        if (transaction.getUser() == null || transaction.getUser().getUserId() == null) {
            throw new IllegalArgumentException("User ID is required in the request body.");
        }
        return transactionService.createTransaction(transaction.getUser().getUserId(), transaction);
    }
}
