package com.cg.student.service;

import java.util.List;

import com.cg.student.entities.Student;

public interface StudentService {
	Student addStudent(Student student);
	List<Student> findAll();
	Student findById(Integer id);
	List<Student> findByFName(String name);
}
