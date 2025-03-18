package com.hyokyunp1.hyokyunp1.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hyokyunp1.hyokyunp1.model.ThymBoard;

public interface BoardRepository extends JpaRepository<ThymBoard, Long>{
	//automatically query would be built.
	
	//for 1 condition
	List<ThymBoard> findByTitle(String title);
	
	//for 2 conditions
	List<ThymBoard> findByTitleOrContent(String title, String content);
	
	//search text(pagination)
	Page<ThymBoard> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
