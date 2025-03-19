package com.hyokyunp1.hyokyunp1.user.controller;

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

import com.hyokyunp1.hyokyunp1.model.ThymUser;
import com.hyokyunp1.hyokyunp1.repository.UserRepository;

@RestController
@RequestMapping("/api")
class UserController {

  @Autowired
  private UserRepository repository;


  @GetMapping("/users")
  List<ThymUser> all() {
	  return repository.findAll();
  }

  @PostMapping("/users")
  ThymUser newuser(@RequestBody ThymUser thymuser) {
    return repository.save(thymuser);
  }

  // Single item
  
  @GetMapping("/users/{id}")
  ThymUser one(@PathVariable Integer id) {
    //return empty object when no any user data
    return repository.findById(id).orElse(new ThymUser());
  }

  @PutMapping("/users/{id}")
  ThymUser replaceuser(@RequestBody ThymUser thymuser, @PathVariable Integer id) {
    //update data or newly insert data
    return repository.findById(id)
      .map(user -> {
    	//additional : board data
    	user.setBoards(thymuser.getBoards());
        return repository.save(user);
      })
      .orElseGet(() -> {
    	thymuser.setId(id);
        return repository.save(thymuser);
      });
  }

  @DeleteMapping("/users/{id}")
  void deleteuser(@PathVariable Integer id) {
    repository.deleteById(id);
  }
}