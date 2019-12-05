package com.gt.GIPHY_App.rest;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gt.GIPHY_App.models.User;
import com.gt.GIPHY_App.repos.UserRepository;

@RestController
public class UserManagerRest {

	private UserRepository repository;
	
	@GetMapping("/getCurrentUser")
	public User getCurrentUser(final HttpSession session) {
		return (User) session.getAttribute("currentUser");
	}
	
	@PostMapping(value = "/register")
	public User register(@RequestParam(name = "email") String email,
						 @RequestParam(name = "userName") String userName,
						 @RequestParam(name = "password") String password) {
		final User newUser = new User(userName, password, email);
		return repository.saveAndFlush(newUser);
	}
}
