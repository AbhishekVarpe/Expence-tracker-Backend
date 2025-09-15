package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dao.userRepo;
import com.example.exception.UserNotFoundException;
import com.example.model.User;
import com.example.security.JwtUtil;



@Service
public class userService {


	    @Autowired
	    private userRepo userRepo;

	    @Autowired
	    private PasswordEncoder passwordEncoder;

	    @Autowired
	    private JwtUtil jwtUtil;

	    public User registerUser(User user) {
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        return userRepo.save(user);
	    }

	    public String loginUser(String username, String password) {
	        User user = userRepo.findByUsername(username).orElse(null);
	        if (user == null) {
	            throw new UserNotFoundException("Invalid credential");
	        }

	        if (!passwordEncoder.matches(password, user.getPassword())) {  
	            throw new UserNotFoundException("Invalid credential");
	        }

	        return jwtUtil.generateToken(user.getUsername());
	    }

	}

