package com.hyokyunp1.hyokyunp1.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.hyokyunp1.hyokyunp1.model.ThymBoard;
import com.hyokyunp1.hyokyunp1.model.ThymUser;

public interface UserRepository extends JpaRepository<ThymUser, Integer>, QuerydslPredicateExecutor<ThymUser>, CustomizedUserRepository{
	//automatically query would be built.
	
	//find user info by username
	ThymUser findByUsername(String username);
	
	//JPQL : %1 = 1st parameter
	@Query("SELECT u FROM ThymUser u WHERE u.username LIKE %?1%")
	List<ThymUser> findWithUsername(String username);
	
	//native query
	@Query(value = "SELECT * FROM THYM_USER U WHERE U.USERNAME LIKE %?1%", nativeQuery = true)
	List<ThymUser> findWithUsernameNativeQuery(String username);
}
