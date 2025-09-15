package com.example.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.User;

public interface userRepo extends JpaRepository<User, Integer> {
	 Optional<User> findByUsername(String username);
}
