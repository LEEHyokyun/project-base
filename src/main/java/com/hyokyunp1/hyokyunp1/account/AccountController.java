package com.hyokyunp1.hyokyunp1.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyokyunp1.hyokyunp1.model.ThymUser;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	
	
	@GetMapping("/login")
	public String login() {
		return "account/login";
	}
	
	@GetMapping("/register")
	public String register() {
		
		//to home after registered
		return "account/register";
	}	
	
	@PostMapping("/register")
	public String register(ThymUser user) {
		
		//register user info
		accountService.save(user);
		
		return "redirect:/";
	}	
}
