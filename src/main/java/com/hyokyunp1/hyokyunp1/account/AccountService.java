package com.hyokyunp1.hyokyunp1.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hyokyunp1.hyokyunp1.model.ThymRole;
import com.hyokyunp1.hyokyunp1.model.ThymUser;
import com.hyokyunp1.hyokyunp1.repository.UserRepository;

@Service
public class AccountService {
	
	@Autowired
	private UserRepository userRepository;
	
	//DI at config
	@Autowired
	private PasswordEncoder passwordEncoder;
		
	public ThymUser save(ThymUser user) {
		//additional data needed : id, password, enabled
		user.setId(1);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setEnabled(1);
		
		//insert USER_ROLE(*ROLE DATA is hard-coded)
		ThymRole role = new ThymRole();
		role.setId(1);
		user.getRoles().add(role);
				
		return userRepository.save(user);
	}
}
