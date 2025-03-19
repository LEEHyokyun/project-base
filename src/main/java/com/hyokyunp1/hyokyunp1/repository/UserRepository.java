package com.hyokyunp1.hyokyunp1.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hyokyunp1.hyokyunp1.model.ThymBoard;
import com.hyokyunp1.hyokyunp1.model.ThymUser;

public interface UserRepository extends JpaRepository<ThymUser, Integer>{
	//automatically query would be built.
	
	//find user info by username
	ThymUser findByUsername(String username);
}
