package edu.ada.library.controller;

import edu.ada.library.model.entity.BookEntity;

public interface LibWS
{
	Object getBooksBy(String name, String category, String authorName);
	Object pickUpBook(String token, Long bookId);
	Object dropOffBook(String token, Long bookId);
}
