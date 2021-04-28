package com.cg.student.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.student.entities.Student;

@Repository
public interface StudentDao extends JpaRepository<Student,Integer>{

	List<Student> findByFirstName(String name);

}
