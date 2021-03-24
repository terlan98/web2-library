package edu.ada.library.service.impl;

import edu.ada.library.model.entity.CourseEntity;
import edu.ada.library.model.entity.UserEntity;
import edu.ada.library.repository.CourseRepository;
import edu.ada.library.repository.UserRepository;
import edu.ada.library.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService
{
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public CourseEntity getCourseById(Long courseId)
	{
		return courseRepository.findById(courseId).orElse(null);
	}
	
	@Override
	public void enrollToCourse(UserEntity user, CourseEntity course)
	{
		user.getCourses().add(course);
		userRepository.save(user);
	}
	
	@Override
	public void dropCourse(UserEntity user, CourseEntity course)
	{
		user.getCourses().remove(course);
		userRepository.save(user);
	}
	
	@Override
	public List<CourseEntity> getAllCourses()
	{
		return courseRepository.findAll();
	}
	
	@Override
	public void addCourseIfNotExists(CourseEntity course)
	{
		if (courseRepository.findFirstByNameIgnoreCase(course.getName()) == null) courseRepository.save(course);
	}
}
