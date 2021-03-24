package edu.ada.library.service.impl;

import edu.ada.library.exception.BookAlreadyTakenException;
import edu.ada.library.exception.BookNotFoundException;
import edu.ada.library.exception.LoanNotFoundException;
import edu.ada.library.model.entity.BookEntity;
import edu.ada.library.model.entity.LoanEntity;
import edu.ada.library.model.entity.UserEntity;
import edu.ada.library.repository.BookRepository;
import edu.ada.library.repository.LoanRepository;
import edu.ada.library.service.LibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibServiceImpl implements LibService
{
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Override
	public BookEntity fetchById(Long id) throws BookNotFoundException
	{
		Optional<BookEntity> book = bookRepository.findById(id);
		if (book.isEmpty()) throw new BookNotFoundException();
		return book.get();
	}
	
	@Override
	public BookEntity fetchByName(String name)
	{
		return bookRepository.findFirstByNameIgnoreCase(name);
	}
	
	@Override
	public List<BookEntity> fetchByCategory(String category)
	{
		return bookRepository.findAllByCategoryIgnoreCase(category);
	}
	
	@Override
	public List<BookEntity> fetchByAuthorName(String authorName)
	{
		return bookRepository.findAllByAuthorIgnoreCase(authorName);
	}
	
	@Override
	public List<BookEntity> fetchByNameAndCategory(String name, String category)
	{
		return bookRepository.findAllByNameAndCategoryIgnoreCase(name, category);
	}
	
	@Override
	public List<BookEntity> fetchByNameAndAuthorName(String name, String authorName)
	{
		return bookRepository.findAllByNameAndAuthorIgnoreCase(name, authorName);
	}
	
	@Override
	public List<BookEntity> fetchByCategoryAndAuthorName(String category, String authorName)
	{
		return bookRepository.findAllByCategoryAndAuthorIgnoreCase(category, authorName);
	}
	
	@Override
	public List<BookEntity> fetchByNameAndCategoryAndAuthorName(String name, String category, String authorName)
	{
		return bookRepository.findAllByNameAndCategoryAndAuthorIgnoreCase(name, category, authorName);
	}
	
	@Override
	public void addBookIfNotExists(BookEntity book)
	{
		if (bookRepository.findFirstByNameIgnoreCase(book.getName()) == null) bookRepository.save(book);
	}
	
	@Override
	public void createLoan(UserEntity user, BookEntity book) throws BookAlreadyTakenException
	{
		LoanEntity loan = loanRepository.findFirstByBook(book);
		
		if(loan != null && !loan.isReturned())
		{
			if(loan.getUser().getId().equals(user.getId()))
			{
				throw new BookAlreadyTakenException("The book is already taken by the current user");
			}
			else
			{
				throw new BookAlreadyTakenException("The book is already taken by another user");
			}
		}
		
		loanRepository.save(new LoanEntity(user, book));
	}
	
	@Override
	public void returnLoan(UserEntity user, BookEntity book) throws LoanNotFoundException
	{
		LoanEntity loan = loanRepository.findFirstByBook(book);
		if (loan == null || loan.isReturned()) throw new LoanNotFoundException();
		loan.setReturned(true);
		loanRepository.save(loan);
	}
}
