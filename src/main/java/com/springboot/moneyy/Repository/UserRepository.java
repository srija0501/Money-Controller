package com.springboot.moneyy.Repository;

import com.springboot.moneyy.Entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
