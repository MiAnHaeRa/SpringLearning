package com.yedam.app.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class WebControllerAdvice {
	@ExceptionHandler(value = NullPointerException.class)
	public ResponseEntity<String> handleSqlExcpetion() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@ModelAttribute("contextPath")
	public String contextPath(final HttpServletRequest req) {
		return req.getContextPath();
	}
}
