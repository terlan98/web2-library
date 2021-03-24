package edu.ada.library.repository;

import edu.ada.library.model.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<BookEntity, Long>
{
	BookEntity findFirstByNameIgnoreCase(String name);
	List<BookEntity> findAllByCategoryIgnoreCase(String category);
	List<BookEntity> findAllByAuthorIgnoreCase(String authorName);
	List<BookEntity> findAllByNameAndCategoryIgnoreCase(String name, String category);
	List<BookEntity> findAllByNameAndAuthorIgnoreCase(String name, String authorName);
	List<BookEntity> findAllByCategoryAndAuthorIgnoreCase(String category, String authorName);
	List<BookEntity> findAllByNameAndCategoryAndAuthorIgnoreCase(String name, String category, String authorName);
}
