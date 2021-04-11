package edu.ada.library.service;

import edu.ada.library.exception.BookAlreadyTakenException;
import edu.ada.library.exception.BookNotFoundException;
import edu.ada.library.exception.LoanNotFoundException;
import edu.ada.library.model.dto.BookModel;
import edu.ada.library.model.entity.BookEntity;
import edu.ada.library.model.entity.LoanEntity;
import edu.ada.library.model.entity.UserEntity;

import java.util.List;

/**
 * Interface to be implemented by classes offering library services.
 */
public interface LibService
{
	List<LoanEntity> fetchLoanByUser(UserEntity user);
	
	List<BookEntity> fetchAll();
	BookModel fetchById(Long id) throws BookNotFoundException;
	BookEntity fetchBookEntityById(Long id) throws BookNotFoundException;
	BookEntity fetchByName(String name);
	List<BookEntity> fetchByCategory(String category);
	List<BookEntity> fetchByAuthorName(String authorName);
	List<BookEntity> fetchByNameAndCategory(String name, String category);
	List<BookEntity> fetchByNameAndAuthorName(String name, String authorName);
	List<BookEntity> fetchByCategoryAndAuthorName(String category, String authorName);
	List<BookEntity> fetchByNameAndCategoryAndAuthorName(String name, String category, String authorName);
	
	void addBookIfNotExists(BookEntity book);
	void createLoan(UserEntity user, BookEntity book) throws BookAlreadyTakenException;
	void returnLoan(UserEntity user, BookEntity book) throws LoanNotFoundException;
}
