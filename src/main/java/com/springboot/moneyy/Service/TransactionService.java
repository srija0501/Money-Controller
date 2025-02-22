package com.springboot.moneyy.Service;

import com.springboot.moneyy.Entity.Transaction;
import com.springboot.moneyy.Entity.User;
import com.springboot.moneyy.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    // Get all transactions
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // Get transactions for a specific user
    public List<Transaction> getTransactionsByUser(Long userId) {
        return transactionRepository.findByUserId(userId);
    }
       

    public List<Transaction> getAllTransactionsSortedByAmount() {
        return transactionRepository.findAllByOrderByAmountAsc();
    }

    // Create a new transaction
    public Transaction createTransaction(Transaction transaction) {
        if (transaction.getUserId() == null) {
            throw new IllegalArgumentException("User ID is required");
        }
        return transactionRepository.save(transaction);
    }

    // Update an existing transaction
    public Transaction updateTransaction(Long id, Transaction transactionDetails) {
        // Check if the transaction exists by ID
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if (optionalTransaction.isEmpty()) {
            throw new RuntimeException("Transaction not found with id: " + id);
        }

        // Get the existing transaction
        Transaction transaction = optionalTransaction.get();

        // Update transaction fields with new data
        transaction.setAmount(transactionDetails.getAmount());
        transaction.setDate(transactionDetails.getDate());
        transaction.setDescription(transactionDetails.getDescription());
        transaction.setCategory(transactionDetails.getCategory());
        transaction.setUserId(transactionDetails.getUserId());

        // Save and return updated transaction
        return transactionRepository.save(transaction);
    }
 
    public Page<Transaction> getAllTransactions(int page, int size) {
        return transactionRepository.findAll(PageRequest.of(page, size));
    }
    
    
    public void deleteTransaction(Long id) {
      
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if (optionalTransaction.isEmpty()) {
            throw new RuntimeException("Transaction not found with id: " + id);
        }

        
        transactionRepository.deleteById(id);
    }
}
