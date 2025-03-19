package com.hyokyunp1.hyokyunp1.repository;

import java.util.List;

import com.hyokyunp1.hyokyunp1.model.ThymUser;

public interface CustomizedUserRepository {
	List<ThymUser> findByUsernameCustomizedQuery(String username);
}
