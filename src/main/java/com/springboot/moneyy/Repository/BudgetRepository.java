package com.springboot.moneyy.Repository;

import com.springboot.moneyy.Entity.Budget;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    @Query("SELECT b FROM Budget b WHERE b.amount >= :amount ORDER BY b.amount ASC")
    List<Budget> findBudgetsByAmountGreaterThanEqual(@Param("amount") Double amount);
}
