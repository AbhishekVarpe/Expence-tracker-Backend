package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Expense;
import com.example.model.User;

public interface expenseRepo extends JpaRepository<Expense, Integer> {
	List<Expense> findByUser(User user);
}
