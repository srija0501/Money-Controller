package com.springboot.moneyy.Repository;

import com.springboot.moneyy.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserId(Long userId);
    List<Transaction> findAllByOrderByAmountAsc();
}
