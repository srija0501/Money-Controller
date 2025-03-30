package com.springboot.moneyy.Service;

import com.springboot.moneyy.Entity.Category;
import com.springboot.moneyy.Entity.Transaction;
import com.springboot.moneyy.Repository.CategoryRepository;
import com.springboot.moneyy.Repository.TransactionRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;

    public CategoryService(CategoryRepository categoryRepository, TransactionRepository transactionRepository) {
        this.categoryRepository = categoryRepository;
        this.transactionRepository = transactionRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category categoryDetails) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new RuntimeException("Category not found with ID: " + id);
        }
    
        Category category = optionalCategory.get();
        category.setName(categoryDetails.getName());
        category.setType(categoryDetails.getType());
    
        // Ensure transactions are updated properly
        if (categoryDetails.getTransactions() != null) {
            for (Transaction transaction : categoryDetails.getTransactions()) {
                transaction.setCategory(category);  // Set the category for each transaction
                transactionRepository.save(transaction);
            }
            category.setTransactions(categoryDetails.getTransactions());
        }
    
        return categoryRepository.save(category);
    }
    

    @Transactional  // ✅ Ensure transaction is active
public void deleteCategory(Long id) {
    Optional<Category> optionalCategory = categoryRepository.findById(id);
    if (optionalCategory.isEmpty()) {
        throw new RuntimeException("Category not found with ID: " + id);
    }

    // Delete transactions first
    transactionRepository.deleteByCategoryId(id);

    // Now delete the category
    categoryRepository.deleteById(id);
}

    
}
