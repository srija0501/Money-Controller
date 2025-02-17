package com.springboot.moneyy.Service;

import com.springboot.moneyy.Entity.Transaction;
import com.springboot.moneyy.Entity.User;
import com.springboot.moneyy.Repository.TransactionRepository;
import com.springboot.moneyy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByUser(Long userId) {
        return transactionRepository.findByUserUserId(userId);
    }

    public Transaction createTransaction(Long userId, Transaction transaction) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
        transaction.setUser(userOptional.get());
        return transactionRepository.save(transaction);
    }
}
