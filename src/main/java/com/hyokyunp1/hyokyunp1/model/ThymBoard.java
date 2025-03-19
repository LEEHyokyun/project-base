package com.hyokyunp1.hyokyunp1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ThymBoard {
	@Id
	private Long id;
	
	private String title;
	private String content;
	
	/*
	 * Many To One
	 * need to map VO Object
	 * 1 User could get many Boards.
	 * name : Linking Table
	 * joinColumns : @JoinColumn : pivot
	 * inverseJoinColumns : @JoinColumn : reference
	 * */
	@ManyToOne
	@JoinColumn(
			name = "USER_ID",
			referencedColumnName = "ID"
			)
	@JsonIgnore
	private ThymUser user;
}
