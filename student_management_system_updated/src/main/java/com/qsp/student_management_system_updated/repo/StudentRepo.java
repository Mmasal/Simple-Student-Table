package com.qsp.student_management_system_updated.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qsp.student_management_system_updated.dto.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {
	Student findStudentByPhone(long phone);

	Student getStudentByEmail(String email);
	
	@Query("SELECT s FROM Student s WHERE s.address=?1")
	List<Student> stuByAddress(String address);
	
	@Query("SELECT s FROM Student s WHERE s.name=?1")
	List<Student> stuByName(String name);

	List<Student>findStudentBysecuredMarksLessThan(int marks);

	List<Student> findStudentBysecuredMarksGreaterThan(int marks);

	@Query("SELECT s FROM Student s WHERE s.securedMarks BETWEEN ?1 AND ?2")
	List<Student> marksBetween(int marks1, int marks2);

	List<Student> getStudentByGrade(String grade);

	
}
