package edu.ada.library.repository;

import edu.ada.library.model.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<CourseEntity, Long>
{
	List<CourseEntity> findAll();
	CourseEntity findFirstByNameIgnoreCase(String courseName);
}
