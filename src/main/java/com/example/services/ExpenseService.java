package com.example.services;
import com.example.dao.userRepo;
import com.example.model.Expense;
import com.example.model.User;
import com.example.security.JwtUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.expenseRepo;

@Service
public class ExpenseService {

	  @Autowired
	    private expenseRepo expenseRepository;

	    @Autowired
	    private userRepo userRepository;

	    @Autowired
	    private JwtUtil jwtutil;  // your existing JWT utility service to extract username

	    // Add Expense
	    public Expense addExpense(String token, Expense expense) {
	        String username = jwtutil.extractUsername(token);
	        User user = userRepository.findByUsername(username)
	                .orElseThrow(() -> new RuntimeException("User not found"));
	        expense.setUser(user);
	        return expenseRepository.save(expense);
	    } 

	    // Get all expenses for logged-in user
	    public List<Expense> getExpensesForUser(String token) {
	        String username = jwtutil.extractUsername(token);
	        User user = userRepository.findByUsername(username)
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        return expenseRepository.findByUser(user);
	    }

	    // Optional: delete expense by id
	    public void deleteExpense(String token, int expenseId) {
	        String username = jwtutil.extractUsername(token);
	        User user = userRepository.findByUsername(username)
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        Expense expense = expenseRepository.findById(expenseId)
	                .orElseThrow(() -> new RuntimeException("Expense not found"));

	        if (expense.getUser().getId() != user.getId()) {
	            throw new RuntimeException("Unauthorized to delete this expense");
	        } 
	        expenseRepository.delete(expense);
	    }
	}
