package com.hyokyunp1.hyokyunp1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hyokyunp1.hyokyunp1.model.ThymBoard;

public interface BoardRepository extends JpaRepository<ThymBoard, Long>{
	
}
