package com.springboot.moneyy.Service;

import com.springboot.moneyy.Entity.Category;
import com.springboot.moneyy.Entity.Transaction;
import com.springboot.moneyy.Entity.User;
import com.springboot.moneyy.Repository.CategoryRepository;
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

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByUser(Long userId) {
        return transactionRepository.findByUserId(userId);
    }
  public Transaction getTransactionById(Long id) {
    return transactionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + id));
}


    public List<Transaction> getAllTransactionsSortedByAmount() {
        return transactionRepository.findAllByOrderByAmountAsc();
    }

    public Transaction createTransaction(Transaction transaction) {
        if (transaction.getUserId() == null || transaction.getCategory() == null) {
            throw new IllegalArgumentException("User ID and Category ID are required");
        }
    
        // Fetch the Category from DB
        Category category = categoryRepository.findById(transaction.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
    
        // Link the transaction to the category
        transaction.setCategory(category);
    
        // Save the transaction
        Transaction savedTransaction = transactionRepository.save(transaction);
    
        // Also update the category's transaction list
        category.getTransactions().add(savedTransaction);
        categoryRepository.save(category); // Ensure it is updated in DB
    
        return savedTransaction;
    }
    

    public Transaction updateTransaction(Long id, Transaction transactionDetails) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if (optionalTransaction.isEmpty()) {
            throw new RuntimeException("Transaction not found with ID: " + id);
        }

        Transaction transaction = optionalTransaction.get();
        transaction.setAmount(transactionDetails.getAmount());
        transaction.setDate(transactionDetails.getDate());
        transaction.setDescription(transactionDetails.getDescription());
        transaction.setUserId(transactionDetails.getUserId());

        // Update category only if provided
        if (transactionDetails.getCategory() != null && transactionDetails.getCategory().getId() != null) {
            Optional<Category> categoryOptional = categoryRepository.findById(transactionDetails.getCategory().getId());
            categoryOptional.ifPresent(transaction::setCategory);
        }

        return transactionRepository.save(transaction);
    }

    public Page<Transaction> getAllTransactions(int page, int size) {
        return transactionRepository.findAll(PageRequest.of(page, size));
    }

    public void deleteTransaction(Long id) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if (optionalTransaction.isEmpty()) {
            throw new RuntimeException("Transaction not found with ID: " + id);
        }
        transactionRepository.deleteById(id);
    }
}
