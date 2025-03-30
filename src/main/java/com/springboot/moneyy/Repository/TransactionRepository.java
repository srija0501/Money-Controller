package com.springboot.moneyy.Repository;

import com.springboot.moneyy.Entity.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    List<Transaction> findByUserId(Long userId);
    
    List<Transaction> findAllByOrderByAmountAsc();
    
    List<Transaction> findByCategoryId(Long categoryId); // ✅ Only keep one declaration

    @Transactional  // ✅ Required for delete queries
    @Modifying
    @Query("DELETE FROM Transaction t WHERE t.category.id = :categoryId")
    void deleteByCategoryId(@Param("categoryId") Long categoryId);
}
