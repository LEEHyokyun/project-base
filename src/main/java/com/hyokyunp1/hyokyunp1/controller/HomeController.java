package com.hyokyunp1.hyokyunp1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	/*
	 * 최초 index.html 홈페이지 호출
	 * */
	@GetMapping
	public String index() {
		return "index";
	}
}
