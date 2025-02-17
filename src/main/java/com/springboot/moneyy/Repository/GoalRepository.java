package com.springboot.moneyy.Repository;

import com.springboot.moneyy.Entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    // You can add custom queries here if needed
}
