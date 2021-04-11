package edu.ada.library.controller.impl;

import edu.ada.library.controller.LibWS;
import edu.ada.library.exception.*;
import edu.ada.library.model.dto.BookModel;
import edu.ada.library.model.dto.LoanModel;
import edu.ada.library.model.entity.BookEntity;
import edu.ada.library.model.entity.LoanEntity;
import edu.ada.library.model.entity.UserEntity;
import edu.ada.library.service.AuthService;
import edu.ada.library.service.CommentService;
import edu.ada.library.service.LibService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lib")
public class LibWSImpl implements LibWS
{
	protected static Logger log = LoggerFactory.getLogger(LibWSImpl.class);
	
	@Autowired
	private LibService libService;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private CommentService commentService;
	
	@Override
	@GetMapping("/all")
	public Object getAllBooks()
	{
		List<BookEntity> books = libService.fetchAll();
		
		return books.stream().map(BookModel::new).collect(Collectors.toList()); // converting books to DTOs
	}
	
	@Override
	@GetMapping("/{bookId}")
	public Object getBookById(@PathVariable Long bookId)
	{
		log.info("Finding book by id :: {}", bookId);
		
		try
		{
			BookModel book = libService.fetchById(bookId);
			
			if (book != null) return ResponseEntity.ok(book);
			else return ResponseEntity.notFound().build();
			
		} catch (BookNotFoundException e)
		{
			return ResponseEntity.notFound().build();
		}
	}
	
	@Override
	@PostMapping(value = "/new-comment/{bookId}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object comment(
			@RequestHeader String token,
			@PathVariable Long bookId,
			@RequestBody String content)
	{
		log.info("New Comment token :: {} ", token);
		log.info("New Comment bookId :: {} ", bookId);
		log.info("New Comment content :: {} ", content);
		
		try
		{
			UserEntity author = authService.findByToken(token);
			BookModel book = libService.fetchById(bookId);
			
			commentService.addComment(author.getFirstName(), book.getId(), content);
			
			return ResponseEntity.ok().build();
			
		} catch (UserNotFoundException | BookNotFoundException e)
		{
			return ResponseEntity.notFound().build();
		}
	}
	
	@Override
	@PostMapping(value = "/reply-to-comment/{commentId}")
	public Object replyToComment(
			@RequestHeader String token,
			@PathVariable String commentId,
			@RequestBody String content)
	{
		log.info("New Reply token :: {} ", token);
		log.info("New Reply commentId :: {} ", commentId);
		log.info("New Reply content :: {} ", content);
		
		try
		{
			UserEntity author = authService.findByToken(token);
			commentService.addReply(author.getFirstName(), commentId, content);
			return ResponseEntity.ok().build();
			
		} catch (UserNotFoundException e)
		{
			return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
		} catch (CommentNotFoundException e)
		{
			return new ResponseEntity("Comment not found", HttpStatus.NOT_FOUND);
		}
	}
	
	@Override
	@GetMapping("/find")
	public Object getBooksBy(
			@RequestHeader(name = "name", required = false) String name,
			@RequestHeader(name = "category", required = false) String category,
			@RequestHeader(name = "authorName", required = false) String authorName)
	{
		List<BookEntity> books = new ArrayList<>();
		
		short decisionVar = 0;
		
		if (name != null) decisionVar += 1;
		if (category != null) decisionVar += 10;
		if (authorName != null) decisionVar += 100;
		
		switch (decisionVar)
		{
			case 1: // name
				log.info("Finding books by name :: {}", name);
				BookEntity book = libService.fetchByName(name);
				if (book != null) books = List.of(book);
				break;
			
			case 10: // category
				log.info("Finding books by category :: {}", category);
				books = libService.fetchByCategory(category);
				break;
			
			case 100: // author
				log.info("Finding books by author :: {}", authorName);
				books = libService.fetchByAuthorName(authorName);
				break;
			
			case 11: // name & category
				log.info("Finding books by name, category :: {}, {}", name, category);
				books = libService.fetchByNameAndCategory(name, category);
				break;
			
			case 101: // name & author
				log.info("Finding books by name, author :: {}, {}", name, authorName);
				books = libService.fetchByNameAndAuthorName(name, authorName);
				break;
			
			case 110: // category & author
				log.info("Finding books by category, author :: {}, {}", category, authorName);
				books = libService.fetchByCategoryAndAuthorName(category, authorName);
				break;
			
			case 111: // all
				log.info("Finding book by name, category, author :: {}, {}, {}", name, category, authorName);
				books = libService.fetchByNameAndCategoryAndAuthorName(name, category, authorName);
				break;
		}
		
		if (books.isEmpty()) return ResponseEntity.notFound().build();
		
		return books.stream().map(BookModel::new).collect(Collectors.toList()); // converting books to DTOs
	}
	
	
	@Override
	@PostMapping("/pickup/{bookId}")
	public Object pickUpBook(
			@RequestHeader String token,
			@PathVariable Long bookId)
	{
		log.info("Pickup Token :: {}", token);
		log.info("Pickup Book Id :: {}", bookId);
		
		if (token == null || bookId == null)
		{
			return ResponseEntity.badRequest().build();
		}
		
		try // finding the user and book and creating a new loan
		{
			UserEntity user = authService.findByToken(token);
			BookEntity book = libService.fetchBookEntityById(bookId);
			libService.createLoan(user, book);
			return ResponseEntity.ok().build();
			
		} catch (UserNotFoundException | BookNotFoundException e)
		{
			return ResponseEntity.notFound().build();
		} catch (BookAlreadyTakenException e)
		{
			return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@Override
	@PostMapping("/dropoff/{bookId}")
	public Object dropOffBook(
			@RequestHeader String token,
			@PathVariable Long bookId)
	{
		log.info("Drop-off Token :: {}", token);
		log.info("Drop-off Book Id :: {}", bookId);
		
		try // finding the user and book and returning the loan
		{
			UserEntity user = authService.findByToken(token);
			BookEntity book = libService.fetchBookEntityById(bookId);
			libService.returnLoan(user, book);
			return ResponseEntity.ok().build();
		} catch (UserNotFoundException | BookNotFoundException | LoanNotFoundException e)
		{
			return ResponseEntity.notFound().build();
		}
	}
	
	@Override
	@GetMapping("/history")
	public Object getLoanHistoryFor(@RequestHeader String token)
	{
		log.info("Loan History Token :: {}", token);
		
		List<LoanEntity> loans = new ArrayList<>();
		
		try
		{
			UserEntity user = authService.findByToken(token);
			loans = libService.fetchLoanByUser(user);
			
		} catch (UserNotFoundException e)
		{
			return new ResponseEntity("Can't find the user", HttpStatus.UNAUTHORIZED);
		}
		
		if (loans.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return loans.stream().map(LoanModel::new).collect(Collectors.toList()); // converting loans to DTOs
	}
	
	@Override
	@GetMapping("/myLoans")
	public Object getCurrentLoansFor(@RequestHeader String token)
	{
		log.info("Current Loans Token :: {}", token);
		
		List<LoanEntity> loans = new ArrayList<>();
		
		try
		{
			UserEntity user = authService.findByToken(token);
			loans = user.getLoans();
			loans = loans.stream().filter(loan -> !loan.isReturned()).collect(Collectors.toList());
			
		} catch (UserNotFoundException e)
		{
			return new ResponseEntity("Can't find the user", HttpStatus.UNAUTHORIZED);
		}
		
		if (loans.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return loans.stream().map(LoanModel::new).collect(Collectors.toList()); // converting loans to DTOs
	}
}
