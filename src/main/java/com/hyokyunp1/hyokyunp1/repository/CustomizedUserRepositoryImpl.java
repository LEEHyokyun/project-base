package com.hyokyunp1.hyokyunp1.repository;

import java.util.List;

import com.hyokyunp1.hyokyunp1.model.QThymUser;
import com.hyokyunp1.hyokyunp1.model.ThymUser;
import com.querydsl.jpa.impl.JPAQuery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class CustomizedUserRepositoryImpl implements CustomizedUserRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<ThymUser> findByUsernameCustomizedQuery(String username) {
		//Customized Query at RepoImpl
		QThymUser qUser = QThymUser.thymUser;
		JPAQuery<?> query = new JPAQuery<Void>(em);
		List<ThymUser> users = query.select(qUser)
				.from(qUser)
				.where(qUser.username.contains(username))
				.fetch();
		
		return users;
	}
}
