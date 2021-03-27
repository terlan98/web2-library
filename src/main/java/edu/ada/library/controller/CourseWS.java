package edu.ada.library.controller;

import edu.ada.library.model.dto.CourseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Handles course-related requests.
 */
public interface CourseWS
{
	/**
	 * Enrolls the user with the given token to the course with the given id.
	 * @param token
	 * @param courseId
	 * @return an HTTP response
	 */
	ResponseEntity enrollToCourse(String token, Long courseId);
	
	/**
	 * Drops the course with the given id from the list of enrolled courses of the user with the given token.
	 * @param token
	 * @param courseId
	 * @return an HTTP response
	 */
	ResponseEntity dropCourse(String token, Long courseId);
	
	/**
	 * Gets the list of all courses.
	 * @param token
	 * @return a List containing all courses
	 */
	List<CourseModel> getAllCourses(String token);
	
	/**
	 * Gets the list of all courses enrolled by the user.
	 * @param token
	 * @return a List containing enrolled courses
	 */
	Object getAllEnrolledCourses(String token);
}
