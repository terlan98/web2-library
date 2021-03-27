package edu.ada.library.service;

import edu.ada.library.model.entity.CourseEntity;
import edu.ada.library.model.entity.UserEntity;

import java.util.List;

/**
 * Interface to be implemented by classes offering course services.
 */
public interface CourseService
{
	List<CourseEntity> getAllCourses();
	CourseEntity getCourseById(Long courseId);
	void enrollToCourse(UserEntity user, CourseEntity course);
	void dropCourse(UserEntity user, CourseEntity course);
	void addCourseIfNotExists(CourseEntity course);
}
