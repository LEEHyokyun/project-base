package com.hyokyunp1.hyokyunp1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyokyunp1.hyokyunp1.model.ThymBoard;
import com.hyokyunp1.hyokyunp1.repository.BoardRepository;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	/*
	 * declare boardRepository to get board data from local DB.
	 * DI, object would be injected while server running
	 * */
	@Autowired
	private BoardRepository boardRepository;
	
	@GetMapping("/list")
	public String list(Model model) {
		
		//1. get data from repository
		List<ThymBoard> board = boardRepository.findAll();
		
		//2. translate to model(thymeleaf component)
		model.addAttribute("boards", board);
		
		return "board/list";
	}
}
