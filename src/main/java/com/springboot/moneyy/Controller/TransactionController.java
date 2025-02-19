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

   
    @GetMapping("/sorted")
    public List<Transaction> getAllTransactionsSortedByAmount() {
        return transactionService.getAllTransactionsSortedByAmount();
    }

    
    @PostMapping("/add")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    // Update an existing transaction
    @PutMapping("/update/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction transactionDetails) {
        return transactionService.updateTransaction(id, transactionDetails);
    }

    // Delete a transaction
    @DeleteMapping("/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return "Transaction deleted successfully!";
    }
}
