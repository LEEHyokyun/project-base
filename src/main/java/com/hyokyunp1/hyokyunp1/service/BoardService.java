package com.hyokyunp1.hyokyunp1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyokyunp1.hyokyunp1.model.ThymBoard;
import com.hyokyunp1.hyokyunp1.model.ThymUser;
import com.hyokyunp1.hyokyunp1.repository.BoardRepository;
import com.hyokyunp1.hyokyunp1.repository.UserRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	/*
	 * added save func : 
	 * board data and user data
	 * user data is involved with authentication.
	 * */
	public ThymBoard save(String username, ThymBoard board) {
		ThymUser user = userRepository.findByUsername(username);
		board.setUser(user);
		
		return boardRepository.save(board);
	}
}
