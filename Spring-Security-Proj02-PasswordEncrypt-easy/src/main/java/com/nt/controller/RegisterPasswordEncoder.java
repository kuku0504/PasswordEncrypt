package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterPasswordEncoder {
    @Autowired
    private PasswordEncoder passwordEncoder;
    
	@PostMapping("/register/{username}/{password}")
	public String registerPwdEncoder(@PathVariable String username, @PathVariable String password) {
		//encrypt the password
		
		String encodedPassword = passwordEncoder.encode(password);
		
		return "user: "+ username+" | encoded password:: "+encodedPassword;
	}
}
