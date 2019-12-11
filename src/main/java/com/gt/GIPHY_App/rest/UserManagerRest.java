package com.gt.GIPHY_App.rest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gt.GIPHY_App.models.User;
import com.gt.GIPHY_App.repos.UserRepository;

@RestController
public class UserManagerRest {

	private final UserRepository repository;

	@Autowired
	public UserManagerRest(final UserRepository repository) {
		this.repository = repository;
	}

	@PostMapping(value = "/logout")
	public String logout(final HttpSession session){
		session.removeAttribute("currentUser");
		return "login.html";
	}
	@PostMapping(value = "/login")
	public String login(@RequestParam(name = "email") 	 final String email,
						@RequestParam(name = "password") final String password,
														 final HttpSession session) {
		final User currentUser = repository.findByEmailAndPassword(email, password);
		if (null != currentUser) {
			session.setAttribute("currentUser", currentUser);
		} else {
			return "login.html";
		}
		return "index.html";
	}

	@GetMapping("/getCurrentUser")
	public User getCurrentUser(final HttpSession session) {
		return (User) session.getAttribute("currentUser");
	}

	@PostMapping(value = "/registerUser")
	public User register(@RequestParam(name = "email") 	  final String email,
						 @RequestParam(name = "username") final String username,
						 @RequestParam(name = "password") final String password) {
		final User newUser = new User(username, password, email);
		return repository.saveAndFlush(newUser);
	}
}
