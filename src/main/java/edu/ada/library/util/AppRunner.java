package edu.ada.library.util;

import edu.ada.library.model.entity.BookEntity;
import edu.ada.library.model.entity.CourseEntity;
import edu.ada.library.service.CourseService;
import edu.ada.library.service.LibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * This class is for automatically adding some dummy data to the database.
 */
@Component
public class AppRunner implements ApplicationRunner
{
	@Autowired
	private LibService libService;
	
	@Autowired
	private CourseService courseService;
	
	
	@Override
	public void run(ApplicationArguments args) throws Exception
	{
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
		
		libService.addBookIfNotExists(new BookEntity("1984", "George Orwell", "Fiction", format.parse("08.06.1949")));
		libService.addBookIfNotExists(new BookEntity("The Fellowship of the Ring (The Lord of the Rings #1)", "J.R.R. Tolkien", "Fiction", format.parse("29.07.1954")));
		libService.addBookIfNotExists(new BookEntity("Robinson Crusoe", "Daniel Defoe", "Adventure", format.parse("25.04.1719")));
		libService.addBookIfNotExists(new BookEntity("Cracking the Coding Interview: 189 Programming Questions and Solutions", "Gayle Laakmann McDowell", "Education", format.parse("01.01.2008")));
		libService.addBookIfNotExists(new BookEntity("Metro 2033", "Dmitry Glukhovsky", "Post-apocalyptic", format.parse("18.03.2010")));
	
		courseService.addCourseIfNotExists(new CourseEntity("Calculus I"));
		courseService.addCourseIfNotExists(new CourseEntity("Web & Mobile II"));
		courseService.addCourseIfNotExists(new CourseEntity("Principles of Distributed Systems"));
		courseService.addCourseIfNotExists(new CourseEntity("Introduction to Computer Networks"));
		courseService.addCourseIfNotExists(new CourseEntity("Linear Algebra"));
	}
}