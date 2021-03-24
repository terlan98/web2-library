package edu.ada.library.controller.impl;

import edu.ada.library.controller.CourseWS;
import edu.ada.library.exception.UserNotFoundException;
import edu.ada.library.model.dto.CourseModel;
import edu.ada.library.model.entity.CourseEntity;
import edu.ada.library.model.entity.UserEntity;
import edu.ada.library.service.AuthService;
import edu.ada.library.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseWSImpl implements CourseWS
{
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private AuthService authService;
	
	
	@Override
	@PostMapping("/enroll/{courseId}")
	public ResponseEntity enrollToCourse(
			@RequestHeader String token,
			@PathVariable Long courseId)
	{
		try
		{
			UserEntity user = authService.findByToken(token);
			CourseEntity course = courseService.getCourseById(courseId);
			courseService.enrollToCourse(user, course);
		} catch (UserNotFoundException e)
		{
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@Override
	@PostMapping("/drop/{courseId}")
	public ResponseEntity dropCourse(
			@RequestHeader String token,
			@PathVariable Long courseId)
	{
		try
		{
			UserEntity user = authService.findByToken(token);
			CourseEntity course = courseService.getCourseById(courseId);
			courseService.dropCourse(user, course);
		} catch (UserNotFoundException e)
		{
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@Override
	@GetMapping("/")
	public List<CourseModel> getAllCourses(
			@RequestHeader String token)
	{
		return courseService.getAllCourses().stream().map(CourseModel::new).collect(Collectors.toList()); // converting courses to DTOs
	}
	
	@Override
	@GetMapping("/enrolled")
	public Object getAllEnrolledCourses(@RequestHeader String token)
	{
		try
		{
			UserEntity user = authService.findByToken(token);
			List<CourseEntity> courses = new ArrayList<>(user.getCourses());
			return courses.stream().map(CourseModel::new).collect(Collectors.toList());
		} catch (UserNotFoundException e)
		{
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
}
