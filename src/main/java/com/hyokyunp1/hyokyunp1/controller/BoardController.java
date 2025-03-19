package com.hyokyunp1.hyokyunp1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hyokyunp1.hyokyunp1.model.ThymBoard;
import com.hyokyunp1.hyokyunp1.repository.BoardRepository;
import com.hyokyunp1.hyokyunp1.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	/*
	 * declare boardRepository to get board data from local DB.
	 * DI, object would be injected while server running
	 * */
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private BoardService boardService;
	
	//page
	@GetMapping("/list")
	public String list(Model model, @PageableDefault(size = 2) Pageable pagable, 
			@RequestParam(required=false, defaultValue = "") String searchText) {
		
		//1. get data from repository
		//List<ThymBoard> board = boardRepository.findAll();
		
		//1-2. get pagable data
		//Page<ThymBoard> board = boardRepository.findAll(pagable);
		Page<ThymBoard> board = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pagable);
		
		//1-2. check page data
		//board.getTotalElements();
		
		//2. translate to model(thymeleaf component)
		model.addAttribute("boards", board);
		
		//3. set page attributes
		model.addAttribute("startPage" , 0);
		model.addAttribute("endPage", board.getTotalPages()-1);
		
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
	
	//add Many To One Related data (*user info at spring security authentication)
	@PostMapping("/form")
	public String boardSubmit(@ModelAttribute ThymBoard board, Authentication authentication) {
		//add data with authentication
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		
		//insert(without pk) or update(with pk)
		boardService.save(authentication.getName(), board);
		//boardRepository.save(board);
		
		/*
		 * redirect board list page
		 * not simply "return the page" 
		 * but request the get post to get updated data
		 * */
		return "redirect:/board/list";
	}
}
