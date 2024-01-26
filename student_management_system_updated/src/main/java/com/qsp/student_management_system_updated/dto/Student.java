package com.qsp.student_management_system_updated.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String address;
	@Column(unique = true)
	private long phone;
	@Column(unique = true)
	private String email;
	private double securedMarks;
	private int totalMarks;
	private double percentage;
	private String grade;

}
