package com.qsp.student_management_system_updated.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qsp.student_management_system_updated.dto.Student;
import com.qsp.student_management_system_updated.repo.StudentRepo;

@Repository
public class StudentDao {
	@Autowired
	StudentRepo studentRepo;

	public Student saveStudent(Student student) {
		return studentRepo.save(student);
	}

	public Student fetchStudent(int id) {
		Optional<Student> optional = studentRepo.findById(id);
		if (optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}

	public List<Student> fetchAll() {
		return studentRepo.findAll();
	}

//	public Student deleteStudent(int id) {
//		Optional<Student> optional = studentRepo.findById(id);
//		if (optional.isPresent()) {
//			studentRepo.deleteById(id);
//			return optional.get();
//		}
//		
//		return null;
//	}
	public Student deleteStudent(Student student) {
		studentRepo.delete(student);
		return student;

	}

//	public Student updateAll(int id, Student employee) {
//
//		Optional<Student> optional = studentRepo.findById(id);
//		if (optional.isPresent()) {
//			employee.setId(id);
//			return studentRepo.save(employee);
//		}
//		return null;
//	}

	public List<Student> saveall(List<Student> list) {
		return studentRepo.saveAll(list);
	}

//	public Student updatePhone(int id, long phone) {
//		Optional<Student> optional = studentRepo.findById(id);
//		if (optional.isPresent()) {
//			Student student = optional.get();
//			student.setPhone(phone);
//			return studentRepo.save(student);
//		}
//		return null;
//	}

//	public Student updateEmail(int id, String email) {
//		Optional<Student> optional = studentRepo.findById(id);
//		if (optional.isPresent()) {
//			Student student = optional.get();
//			student.setEmail(email);
//			return studentRepo.save(student);
//		}
//		return null;
//	}

//	public Student updateMarks(int id, int marks) {
//		Optional<Student> optional = studentRepo.findById(id);
//		if (optional.isPresent()) {
//			Student student = optional.get();
//			student.setSecuredMarks(marks);
//			return studentRepo.save(student);
//		}
//		return null;
//	}

	// using find By keyword
	public Student findByPhone(int phone) {
		return studentRepo.findStudentByPhone(phone);
	}

	// using get By keyword
	public Student findByEmail(String email) {
		// return studentRepo.findStudentByEmail(email);
		return studentRepo.getStudentByEmail(email);
	}

	public List<Student> findByAddress(String address) {
		return studentRepo.stuByAddress(address);
	}

	public List<Student> findByName(String name) {
		return studentRepo.stuByName(name);
	}

	public List<Student> marksIsLessThan(int marks) {
		return studentRepo.findStudentBysecuredMarksLessThan(marks);
	}

	public List<Student> marksIsGreaterThan(int marks) {
		return studentRepo.findStudentBysecuredMarksGreaterThan(marks);
	}

	public List<Student> marksBetween(int marks1, int marks2) {
		return studentRepo.marksBetween(marks1,marks2);
	}

	public List<Student> findByGrade(String grade) {
		return studentRepo.getStudentByGrade(grade);
	}

	
}
