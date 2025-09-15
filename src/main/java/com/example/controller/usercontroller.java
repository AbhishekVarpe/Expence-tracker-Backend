package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.JwtResponse;
import com.example.dto.logindto;
import com.example.model.User;
import com.example.services.userService;



@RestController
public class usercontroller {	

	 @Autowired
	 private userService userService;
	 
	    @PostMapping("/reg")
	    public User register(@RequestBody User user) {
	        return userService.registerUser(user);
	    }

	    @PostMapping("/log")
	    public ResponseEntity<JwtResponse> loginUser(@RequestBody logindto loginRequest) {
	        String token = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
	        return ResponseEntity.ok(new JwtResponse(token));
	    }
}
