package com.cg.student.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.student.dto.StudentDetails;
import com.cg.student.entities.Student;
import com.cg.student.entities.Course;
import com.cg.student.service.StudentService;
import com.cg.student.util.StudentUtil;

@RestController
@RequestMapping("/students")
@Validated
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	@Autowired
	private StudentUtil studentUtil;
	
	@RequestMapping("/hello")
	public String great() {
		return "Hello from student controller";
	}
	@GetMapping("/by/id/{id}")
	public StudentDetails fetchStudent(@PathVariable("id")Integer id)
	{
		Student stud=service.findById(id);
		StudentDetails details=studentUtil.toDetails(stud);
		return details;
		
	}
	@GetMapping("/by/name/{name}")
	public List<StudentDetails> findByName(@PathVariable("name") String name){
		List<Student> list = service.findByFName(name);
		List<StudentDetails> studList = studentUtil.toDetails(list);
		return studList; 
	}
	
	@GetMapping
	public List<StudentDetails> findAll(){
		List<Student> list = service.findAll();
		List<StudentDetails> studList = studentUtil.toDetails(list);
		return studList;
	}
	
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/add")
	public StudentDetails addStudent(@RequestBody @Valid CreateStudentRequest requestData) {
		System.out.println("req data: " + requestData);
		Student student = new Student(requestData.getFirstName(), requestData.getLastName(), requestData.getAge());
		Set<Course> courseSet = requestData.getCourses();
		if (courseSet != null) {
			for (Course course : courseSet) {
				student.addCourse(course);
			}
		}
		System.out.println("stud came: " + student);
		Student stud = service.addStudent(student);
		StudentDetails details = studentUtil.toDetails(stud);
		return details;

	}
}
