package com.hyokyunp1.hyokyunp1.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class ThymRole {
	@Id
	private Integer id;
	
	private String name;
	
	//after configure pivot table
	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
	private List<ThymUser> users;
}
