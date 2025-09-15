package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Expense;
import com.example.services.ExpenseService;


@RestController
@RequestMapping("/expenses")
public class expensecontroller {

    @Autowired
    private ExpenseService expenseService;

    // Add expense
    @PostMapping("/add")
    public ResponseEntity<Expense> addExpense(@RequestHeader("Authorization") String authHeader,
                                              @RequestBody Expense expense) {
        String token = authHeader.substring(7);                                               // remove 'Bearer ' prefix
        Expense savedExpense = expenseService.addExpense(token, expense);
        return ResponseEntity.ok(savedExpense);
    }

    // Get expenses for logged-in user
    @GetMapping("/list")
    public ResponseEntity<List<Expense>> getExpenses(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        List<Expense> expenses = expenseService.getExpensesForUser(token);
        return ResponseEntity.ok(expenses);
    }

    // Delete expense
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExpense(@RequestHeader("Authorization") String authHeader,
                                                @PathVariable int id) {
        String token = authHeader.substring(7);
        expenseService.deleteExpense(token, id);
        return ResponseEntity.ok("Expense deleted successfully");
    }
}