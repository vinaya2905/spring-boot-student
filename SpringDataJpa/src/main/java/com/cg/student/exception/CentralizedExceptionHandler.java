package com.cg.student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CentralizedExceptionHandler {

	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	@ExceptionHandler(StudentNotFoundException.class)
	public String handleStudentNotFound(StudentNotFoundException e)
	{
		return e.getMessage();
	}
}
