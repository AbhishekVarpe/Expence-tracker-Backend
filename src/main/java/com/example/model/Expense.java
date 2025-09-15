package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Expense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String expenseName;
	private int amount;
	private String description;
	
	 @ManyToOne
	 @JoinColumn(name = "user_id")
	 private User user;

	 public int getId() {
		 return id;
	 }

	 public void setId(int id) {
		 this.id = id;
	 }

	 public String getExpenseName() {
		 return expenseName;
	 }

	 public void setExpenseName(String expensename) {
		 this.expenseName = expensename;
	 }

	 public int getAmount() {
		 return amount;
	 }

	 public void setAmount(int amount) {
		 this.amount = amount;
	 }

	 public String getDescription() {
		 return description;
	 }

	 public void setDescription(String description) {
		 this.description = description;
	 }

	 public User getUser() {
		 return user;
	 }

	 public void setUser(User user) {
		 this.user = user;
	 }

	 public Expense(int id, String expensename, int amount, String description, User user) {
		super();
		this.id = id;
		this.expenseName = expensename;
		this.amount = amount;
		this.description = description;
		this.user = user;
	 }

	 public Expense() {
		super();
		// TODO Auto-generated constructor stub
	 }

	
	 
}
