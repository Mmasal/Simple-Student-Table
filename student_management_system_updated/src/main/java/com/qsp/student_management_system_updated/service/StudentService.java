package com.qsp.student_management_system_updated.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.qsp.student_management_system_updated.Exception.SecureMarksShouldBeLessThanTotalMarks;
import com.qsp.student_management_system_updated.dao.StudentDao;
import com.qsp.student_management_system_updated.dto.Student;
import com.qsp.student_management_system_updated.util.ResponseStructure;

@Service
public class StudentService {
	@Autowired
	StudentDao dao;
	ResponseStructure<Student> structure = new ResponseStructure<>();

	public ResponseStructure<Student> saveStudent(Student student) {

		if (student.getSecuredMarks() < student.getTotalMarks()) {
			double percentage = (student.getSecuredMarks() / student.getTotalMarks()) * 100;
			student.setPercentage(percentage);

			if (percentage >= 90) {
				student.setGrade("Distingtion");
			} else if (percentage < 90 && percentage >= 75) {
				student.setGrade("FirstClass");
			} else if (percentage < 75 && percentage >= 60) {
				student.setGrade("SecondClass");
			} else if (percentage < 60 && percentage >= 35) {
				student.setGrade("Pass");
			} else {
				student.setGrade("TuGadhaHai");
			}

			structure.setMessage("Employee Save Sucessful");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(dao.saveStudent(student));
			return structure;
		} else {
			throw new SecureMarksShouldBeLessThanTotalMarks("Secure Marks Should Be Less Than Total Marks");
		}
	}

	public ResponseStructure<Student> fetchStudent(int id) {
		Student student = dao.fetchStudent(id);
		if (student != null) {
			structure.setMessage("Student Details Found Sucessful");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(student);
			return structure;
		} else {
			structure.setMessage("Student with given id is Not Found");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setData(student);
			return structure;
		}
	}

	public ResponseStructure<List<Student>> fetchAll() {
		ResponseStructure<List<Student>> listStructure = new ResponseStructure<List<Student>>();
		List<Student> list = dao.fetchAll();
		if (list.isEmpty()) {
			listStructure.setMessage("No Data Available");
			listStructure.setStatus(HttpStatus.NOT_FOUND.value());
			listStructure.setData(list);
			return listStructure;
		} else {
			listStructure.setMessage("Student Details Found Sucessful");
			listStructure.setStatus(HttpStatus.FOUND.value());
			listStructure.setData(list);
			return listStructure;
		}
	}

	public ResponseStructure<Student> deleteStudent(int id) {

		Student student = dao.fetchStudent(id);
		if (student != null) {
			structure.setMessage("Student Deleted Sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dao.deleteStudent(student));
			return structure;
		} else {
			structure.setMessage("Student with given id is Not Found");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setData(student);
			return structure;
		}
	}

	public ResponseStructure<Student> updateAll(int id, Student student) {
		Student dbstudent = dao.fetchStudent(id);
		if (dbstudent != null) {
			student.setId(id);
			if (student.getSecuredMarks() < student.getTotalMarks()) {
				double percentage = (student.getSecuredMarks() / student.getTotalMarks()) * 100;
				student.setPercentage(percentage);

				if (percentage >= 90) {
					student.setGrade("Distingtion");
				} else if (percentage < 90 && percentage >= 75) {
					student.setGrade("FirstClass");
				} else if (percentage < 75 && percentage >= 60) {
					student.setGrade("SecondClass");
				} else if (percentage < 60 && percentage >= 35) {
					student.setGrade("Pass");
				} else {
					student.setGrade("TuGadhaHai");
				}
				structure.setMessage("Student Save Sucessful");
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setData(dao.saveStudent(student));
				return structure;
			} else {
				throw new SecureMarksShouldBeLessThanTotalMarks("Secure Marks Should Be Less Than Total Marks");
			}
		} else {
			structure.setMessage("Student with given id is Not Found");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setData(dao.saveStudent(student));
			return structure;
		}
	}

	public ResponseStructure<List<Student>> saveall(List<Student> list) {
		for (Student student : list) {
			if (student.getSecuredMarks() < student.getTotalMarks()) {
				double percentage = (student.getSecuredMarks() / student.getTotalMarks()) * 100;
				student.setPercentage(percentage);

				if (percentage >= 90) {
					student.setGrade("Distingtion");
				} else if (percentage < 90 && percentage >= 75) {
					student.setGrade("FirstClass");
				} else if (percentage < 75 && percentage >= 60) {
					student.setGrade("SecondClass");
				} else if (percentage < 60 && percentage >= 35) {
					student.setGrade("Pass");
				} else {
					student.setGrade("TuGadhaHai");
				}
			} else {
				throw new SecureMarksShouldBeLessThanTotalMarks("Secure Marks Should Be Less Than Total Marks");
			}
		}

		List<Student> list2 = dao.saveall(list);
		ResponseStructure<List<Student>> listStructure = new ResponseStructure<>();
		listStructure.setMessage("Saved all the data Sucessfully");
		listStructure.setStatus(HttpStatus.CREATED.value());

		listStructure.setData(dao.saveall(list));
		return listStructure;

	}

	public ResponseStructure<Student> updatePhone(int id, long phone) {
		Student student = dao.fetchStudent(id);
		if (student != null) {
			student.setPhone(phone);
			structure.setMessage("Update Phone Sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dao.saveStudent(student));
			return structure;
		}
		structure.setMessage("Student with given id is Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(student);
		return structure;
	}

	public ResponseStructure<Student> updateEmail(int id, String email) {
		Student student = dao.fetchStudent(id);
		if (student != null) {
			student.setEmail(email);

			structure.setMessage("Update email Sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dao.saveStudent(student));
			return structure;
		} else {
			structure.setMessage("Student with given id is Not Found");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setData(student);
			return structure;
		}
	}

	public Student updateMarks(int id, int securedMarks) {
		Student student = dao.fetchStudent(id);
		if (student != null) {
			student.setSecuredMarks(securedMarks);
			if (student.getSecuredMarks() < student.getTotalMarks()) {
				double percentage = (student.getSecuredMarks() / student.getTotalMarks()) * 100;
				student.setPercentage(percentage);

				if (percentage >= 90) {
					student.setGrade("Distingtion");
				} else if (percentage < 90 && percentage >= 75) {
					student.setGrade("FirstClass");
				} else if (percentage < 75 && percentage >= 60) {
					student.setGrade("SecondClass");
				} else if (percentage < 60 && percentage >= 35) {
					student.setGrade("Pass");
				} else {
					student.setGrade("TuGadhaHai");
				}
				structure.setMessage("Update Marks Sucessfully");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dao.saveStudent(student));
				return student;
			} else {
				throw new SecureMarksShouldBeLessThanTotalMarks("Secure Marks Should Be Less Than Total Marks");
			}
		}
		structure.setMessage("Student with given id is Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(dao.saveStudent(student));
		return student;
	}

	public ResponseStructure<Student> findByPhone(int phone) {
		Student student = dao.findByPhone(phone);
		if (student != null) {
			structure.setMessage("Student Found Sucessfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(student);
			return structure;
		} else {
			structure.setMessage("Employee with given phone is Not Found");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setData(student);
			return structure;
		}
	}

	public ResponseStructure<Student> findByEmail(String email) {
		Student student = dao.findByEmail(email);
		if (student != null) {
			structure.setMessage("Student Found Sucessfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(student);
			return structure;
		} else {
			structure.setMessage("Employee with given email is Not Found");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setData(student);
			return structure;
		}
	}

	public ResponseStructure<List<Student>> findByAddress(String address) {
		List<Student> students = dao.findByAddress(address);
		ResponseStructure<List<Student>> listStructure = new ResponseStructure<>();
		if (students.isEmpty()) {
			listStructure.setMessage("students with given address is Not Found");
			listStructure.setStatus(HttpStatus.NOT_FOUND.value());
			listStructure.setData(students);
			return listStructure;

		} else {

			listStructure.setMessage("students Found Sucessfully");
			listStructure.setStatus(HttpStatus.FOUND.value());
			listStructure.setData(students);
			return listStructure;
		}

	}

	public ResponseStructure<List<Student>> findByName(String name) {
		List<Student> students = dao.findByName(name);
		ResponseStructure<List<Student>> listStructure = new ResponseStructure<>();
		if (students.isEmpty()) {
			listStructure.setMessage("students with given name is Not Found");
			listStructure.setStatus(HttpStatus.NOT_FOUND.value());
			listStructure.setData(students);
			return listStructure;

		} else {

			listStructure.setMessage("students Found Sucessfully");
			listStructure.setStatus(HttpStatus.FOUND.value());
			listStructure.setData(students);
			return listStructure;
		}
	}

	public ResponseStructure<List<Student>> marksIsLessThan(int marks) {
		List<Student> students = dao.marksIsLessThan(marks);
		ResponseStructure<List<Student>> listStructure = new ResponseStructure<>();
		if (students.isEmpty()) {
			listStructure.setMessage("students with less than given marks is Not Found");
			listStructure.setStatus(HttpStatus.NOT_FOUND.value());
			listStructure.setData(students);
			return listStructure;

		} else {

			listStructure.setMessage("students Found Sucessfully");
			listStructure.setStatus(HttpStatus.FOUND.value());
			listStructure.setData(students);
			return listStructure;
		}
	}


	public ResponseStructure<List<Student>> marksIsGreaterThan(int marks) {
		List<Student>students= dao.marksIsGreaterThan(marks);
		ResponseStructure<List<Student>> listStructure = new ResponseStructure<>();
		if (students.isEmpty()) {
			listStructure.setMessage("students with greater than given marks is Not Found");
			listStructure.setStatus(HttpStatus.NOT_FOUND.value());
			listStructure.setData(students);
			return listStructure;

		} else {

			listStructure.setMessage("students Found Sucessfully");
			listStructure.setStatus(HttpStatus.FOUND.value());
			listStructure.setData(students);
			return listStructure;
		}
	}

	public ResponseStructure<List<Student>> marksBetween(int marks1, int marks2) {
		List<Student>students= dao.marksBetween(marks1, marks2);
		ResponseStructure<List<Student>> listStructure = new ResponseStructure<>();
		if (students.isEmpty()) {
			listStructure.setMessage("students with given condition is Not Found");
			listStructure.setStatus(HttpStatus.NOT_FOUND.value());
			listStructure.setData(students);
			return listStructure;

		} else {

			listStructure.setMessage("students Found Sucessfully");
			listStructure.setStatus(HttpStatus.FOUND.value());
			listStructure.setData(students);
			return listStructure;
		}
	}

	public ResponseStructure<List<Student>> findByGrade(String grade) {
		List<Student>students= dao.findByGrade(grade);
		ResponseStructure<List<Student>> listStructure = new ResponseStructure<>();
		if (students.isEmpty()) {
			listStructure.setMessage("students with given grade is Not Found");
			listStructure.setStatus(HttpStatus.NOT_FOUND.value());
			listStructure.setData(students);
			return listStructure;

		} else {

			listStructure.setMessage("students Found Sucessfully");
			listStructure.setStatus(HttpStatus.FOUND.value());
			listStructure.setData(students);
			return listStructure;
		}
	}
}
