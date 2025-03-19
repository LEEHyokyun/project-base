package com.hyokyunp1.hyokyunp1.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class ThymUser {
	@Id
	private Integer id;
	
	private String username;
	private String password;
	private Integer enabled;
	
	/*
	 * Many To Many
	 * need to map VO Object
	 * 1 User could get many Roles.
	 * name : Linking Table
	 * joinColumns : @JoinColumn : pivot
	 * inverseJoinColumns : @JoinColumn : reference
	 * */
	@ManyToMany
	@JoinTable(
			name = "THYM_USER_ROLE",
			joinColumns = @JoinColumn(name = "USER_ID"),
			inverseJoinColumns = @JoinColumn(name= "ROLE_ID")
			)
	private List<ThymRole> roles = new ArrayList<ThymRole>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ThymBoard> boards = new ArrayList<>();
}
