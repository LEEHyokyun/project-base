package com.hyokyunp1.hyokyunp1.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hyokyunp1.hyokyunp1.model.ThymBoard;
import com.hyokyunp1.hyokyunp1.repository.BoardRepository;

@RestController
@RequestMapping("/api")
class Controller {

  @Autowired
  private BoardRepository repository;


  @GetMapping("/boards")
  List<ThymBoard> all(@RequestParam(required=false, defaultValue = "") String title,
		  			  @RequestParam(required=false, defaultValue = "") String content) {
	  /*
	   * url would be
	   * "/api/board?title=~"
	   * */
	
	//search data when title is given, show all data when it is not.
	if(("".equals(title) || title == null) && ("".equals(content) || content == null)) {
		return repository.findAll();
	}else {
    //add specific search interface method first!
		return repository.findByTitleOrContent(title, content);
	}
    
  }

  @PostMapping("/boards")
  ThymBoard newBoard(@RequestBody ThymBoard thymboard) {
    return repository.save(thymboard);
  }

  // Single item
  
  @GetMapping("/boards/{id}")
  ThymBoard one(@PathVariable Long id) {
    //return empty object when no any board data
    return repository.findById(id).orElse(new ThymBoard());
  }

  @PutMapping("/boards/{id}")
  ThymBoard replaceBoard(@RequestBody ThymBoard thymboard, @PathVariable Long id) {
    //update data or newly insert data
    return repository.findById(id)
      .map(board -> {
    	  board.setTitle(thymboard.getTitle());
    	  board.setContent(thymboard.getContent());
        return repository.save(board);
      })
      .orElseGet(() -> {
    	thymboard.setId(id);
        return repository.save(thymboard);
      });
  }

  @DeleteMapping("/boards/{id}")
  void deleteBoard(@PathVariable Long id) {
    repository.deleteById(id);
  }
}