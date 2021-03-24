package edu.ada.library.controller;

import edu.ada.library.model.dto.CourseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CourseWS
{
	ResponseEntity enrollToCourse(String token, Long courseId);
	ResponseEntity dropCourse(String token, Long courseId);
	List<CourseModel> getAllCourses(String token);
	Object getAllEnrolledCourses(String token);
}
