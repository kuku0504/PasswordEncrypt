package com.nt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterPasswordEncoder {
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    private Map<String, String> users=new HashMap<>();
    
	@PostMapping("/register/{username}/{password}")
	public String registerPwdEncoder(@PathVariable String username, @PathVariable String password) {
		//encrypt the password
		
		String encodedPassword = passwordEncoder.encode(password);
		System.out.println("no one");
		return "user: "+ username+" | encoded password:: "+encodedPassword;
	}
	
	@PostMapping("/registerSaveInMap")
	public String PwdEncoderRegistration(@RequestParam String username, @RequestParam String password) {
		//encrypt the password
		String encodedPassword=passwordEncoder.encode(password);
		users.put(username, encodedPassword);
		return "user registered "+username;
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestParam String username, @RequestParam String password) {
		String storedPassword=users.get(username);
		if(storedPassword==null) {
			return "user not found";
		}
		
		//now comapring the encoded password with the given password
		boolean passwordMatch=passwordEncoder.matches(password, storedPassword);
		if(passwordMatch) {
			return "login successful";
		}
		else {
			return "unsuccessful login";
		}
	}
	
}
