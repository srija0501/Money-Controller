package com.springboot.moneyy.Logger;

import com.springboot.moneyy.Entity.User;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Pointcut for createUser method in UserService
    @Pointcut("execution(* com.springboot.moneyy.Service.UserService.createUser(..))")
    public void createUserMethod() {}

    @Before("createUserMethod()")
    public void logBeforeCreateUser() {
        logger.info("Creating a new user...");
    }

    @AfterReturning(pointcut = "createUserMethod()", returning = "result")
    public void logAfterCreateUser(Object result) {
        logger.info("Successfully created user with ID: {}", ((User) result).getUserId());
    }

    // Pointcut for getUserById method
    @Pointcut("execution(* com.springboot.moneyy.Service.UserService.getUserById(..))")
    public void getUserByIdMethod() {}

    @Before("getUserByIdMethod()")
    public void logBeforeGetUserById() {
        logger.info("Fetching user by ID...");
    }

    @AfterReturning(pointcut = "getUserByIdMethod()", returning = "result")
    public void logAfterGetUserById(Object result) {
        if (result != null) {
            logger.info("Successfully fetched user with ID: {}", ((User) result).getUserId());
        } else {
            logger.info("No user found with the given ID.");
        }
    }

    // Pointcut for getAllUsers method
    @Pointcut("execution(* com.springboot.moneyy.Service.UserService.getAllUsers(..))")
    public void getAllUsersMethod() {}

    @Before("getAllUsersMethod()")
    public void logBeforeGetAllUsers() {
        logger.info("Fetching all users...");
    }

    @AfterReturning(pointcut = "getAllUsersMethod()", returning = "result")
    public void logAfterGetAllUsers(Object result) {
        logger.info("Successfully fetched all users, count: {}", ((List<?>) result).size());
    }

    // Pointcut for updateUser method
    @Pointcut("execution(* com.springboot.moneyy.Service.UserService.updateUser(..))")
    public void updateUserMethod() {}

    @Before("updateUserMethod()")
    public void logBeforeUpdateUser() {
        logger.info("Updating user...");
    }

    @AfterReturning(pointcut = "updateUserMethod()", returning = "result")
    public void logAfterUpdateUser(Object result) {
        logger.info("Successfully updated user with ID: {}", ((User) result).getUserId());
    }

    // Pointcut for deleteUser method
    @Pointcut("execution(* com.springboot.moneyy.Service.UserService.deleteUser(..))")
    public void deleteUserMethod() {}

    @Before("deleteUserMethod()")
    public void logBeforeDeleteUser() {
        logger.info("Deleting user...");
    }

    @AfterReturning(pointcut = "deleteUserMethod()")
    public void logAfterDeleteUser() {
        logger.info("Successfully deleted a user.");
    }
}
