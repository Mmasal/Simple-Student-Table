package com.qsp.student_management_system_updated.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.student_management_system_updated.dao.StudentDao;
import com.qsp.student_management_system_updated.dto.Student;
import com.qsp.student_management_system_updated.service.StudentService;
import com.qsp.student_management_system_updated.util.ResponseStructure;

@RestController
public class StudentController {
	@Autowired
	StudentService service;
	
	@Autowired
	StudentDao studentDao;
	
	@PostMapping("/save")
	public ResponseStructure<Student> saveStudent(@RequestBody Student student) {
		return service.saveStudent(student);
	}
	
	@GetMapping("/fetch")
	public ResponseStructure<Student> featchStudent(@RequestParam int id) {
		return service.fetchStudent(id);
	}
	
	@GetMapping("fetchall")
	public ResponseStructure<List<Student>> fetchAll() {
		return service.fetchAll();
	}
	
	@DeleteMapping("/delete")
	public ResponseStructure<Student> deleteStudent(@RequestParam int id) {
		return service.deleteStudent(id);
	}
	
	@PutMapping("/updateall")
	public ResponseStructure<Student> updateEmployee(@RequestParam int id,@RequestBody Student employee) {
		return service.updateAll(id,employee);
	}
	
	@PostMapping("/saveall")
	public ResponseStructure<List<Student>> saveall(@RequestBody List<Student> list){
		return service.saveall(list);
	}
	
	//without using @RequestParam annotation  ,@RequestParam annotation is not mandetory
	@PatchMapping("/updatephone")
	public ResponseStructure<Student> updatePhone(int id,long phone) {
		return service.updatePhone(id,phone);
	}
	
	//with using @RequestParam annotation
	@PatchMapping("updateemail")
	public ResponseStructure<Student> updateEmail(@RequestParam int id,String email) {
		return service.updateEmail(id, email);
	}
	
	//with using @PathVariable
	@PatchMapping("updatemarks/{id}/{securedMarks}")
	public Student updateMarks(@PathVariable int id, @PathVariable int securedMarks) {
		return service.updateMarks(id,securedMarks);
	}
	
	@GetMapping("/findbyphone")
	public ResponseStructure<Student> findByPhone(@RequestParam int phone) {
		return service.findByPhone(phone);
	}
	
	@GetMapping("/findbyemail")
	public ResponseStructure<Student> findByEmail(@RequestParam String email) {
		return service.findByEmail(email);
	}
	
	@GetMapping("/findbyaddress")
	public ResponseStructure<List<Student>> findByAddress(@RequestParam String address){
		return service.findByAddress(address);
	}
	
	@GetMapping("/findbyname")
	public ResponseStructure<List<Student>> findByName(@RequestParam String name){
		return service.findByName(name);
	}
	
	@GetMapping("/markslessthan")
	public ResponseStructure<List<Student>> marksIsLessThan(@RequestParam int marks ){
		return service.marksIsLessThan(marks);
	}
	
	@GetMapping("/marksgreaterthan")
	public ResponseStructure<List<Student>> marksIsGreaterThan(@RequestParam int marks ){
		return service.marksIsGreaterThan(marks);
	} 
	
	@GetMapping("/marksbetween")
	public ResponseStructure<List<Student>> marksBetween(@RequestParam int marks1,int marks2 ){
		return service.marksBetween(marks1,marks2);
	}
	
	@GetMapping("/findbygrade")
	public ResponseStructure<List<Student>> findByGrade(@RequestParam String grade ){
		return service.findByGrade(grade);
	}
	
	
}
