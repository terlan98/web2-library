package edu.ada.library.service;

import edu.ada.library.exception.CommentNotFoundException;
import edu.ada.library.model.dto.CommentModel;

import java.util.List;

/**
 * Interface to be implemented by classes offering comment services.
 */
public interface CommentService
{
	List<CommentModel> getCommentsForBook(Long bookId);
	void addComment(String authorName, Long bookId, String content);
	void addReply(String authorName, String commentId, String content) throws CommentNotFoundException;
}
