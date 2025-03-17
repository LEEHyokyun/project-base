package com.hyokyunp1.hyokyunp1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	//page
	@GetMapping("/list")
	public String list(Model model) {
		
		//1. get data from repository
		List<ThymBoard> board = boardRepository.findAll();
		
		//2. translate to model(thymeleaf component)
		model.addAttribute("boards", board);
		
		return "board/list";
	}
	
	//page with request param(board num)
	@GetMapping("/form")	public String form(Model model, @RequestParam(required=false) Long id) {
		if(id == null) {
			//set Object to submit(mapped to th:object) without id value
			model.addAttribute("board", new ThymBoard());
		}else {
			//set Object to submit(mapped to th:object) with id value(to update) (*get data with id first)
			model.addAttribute("board", boardRepository.findById(id).orElse(new ThymBoard()));
		}
		
		
		//return html page
		return "board/form";
	}
	
	@PostMapping("/form")
	public String boardSubmit(@ModelAttribute ThymBoard board) {
		//insert(without pk) or update(with pk)
		boardRepository.save(board);
		
		/*
		 * redirect board list page
		 * not simply "return the page" 
		 * but request the get post to get updated data
		 * */
		return "redirect:/board/list";
	}
}
