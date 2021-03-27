package edu.ada.library.controller;

/**
 * Handles all book-related requests.
 */
public interface LibWS
{
	/**
	 * Provides the ability to fetch books based on name, category, and/or author name.
	 * @param name
	 * @param category
	 * @param authorName
	 * @return the list of books found OR an HTTP response indicating what went wrong
	 */
	Object getBooksBy(String name, String category, String authorName);
	
	/**
	 * Marks the book with the given ID as 'taken' by the user with the given token.
	 * @param token
	 * @param bookId
	 * @return an HTTP response
	 */
	Object pickUpBook(String token, Long bookId);
	
	/**
	 * Marks the book with the given ID as 'returned' by the user with the given token.
	 * @param token
	 * @param bookId
	 * @return an HTTP response
	 */
	Object dropOffBook(String token, Long bookId);
	
	/**
	 * Gets the history of pick-up/drop-offs performed by the user with the given token.
	 * @param token
	 * @return the list of books OR an HTTP response indicating what went wrong
	 */
	Object getLoanHistoryFor(String token);
	
	/**
	 * Gets the list of books that the user with the given token has taken but not returned yet.
	 * @param token
	 * @return the list of books OR an HTTP response indicating what went wrong
	 */
	Object getCurrentLoansFor(String token);
}
