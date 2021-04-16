package edu.ada.library.service.impl;

import edu.ada.library.exception.CommentNotFoundException;
import edu.ada.library.model.dto.CommentModel;
import edu.ada.library.model.entity.CommentEntity;
import edu.ada.library.repository.CommentRepository;
import edu.ada.library.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService
{
	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public void addComment(String authorName, Long bookId, String content)
	{
		CommentEntity comment = new CommentEntity(authorName, bookId, content);
		commentRepository.save(comment);
	}
	
	@Override
	public void addReply(String authorName, String commentId, String content) throws CommentNotFoundException
	{
		Optional<CommentEntity> comment = commentRepository.findById(commentId); //finding the comment
		if(comment.isEmpty()) throw new CommentNotFoundException();
		
		CommentEntity reply = new CommentEntity(authorName, comment.get().getBookId(), content);
		comment.get().getReplies().add(reply); //adding reply to the found comment
		commentRepository.save(comment.get());
	}
	
	@Override
	public List<CommentModel> getCommentsForBook(Long bookId)
	{
		List<CommentModel> commentModels = new ArrayList<>(1);
		Optional<List<CommentEntity>> commentEntities = commentRepository.findAllByBookId(bookId);
		if(commentEntities.isEmpty()) return commentModels;
		
		if(!commentEntities.get().isEmpty())
		{
			// Converting comment entities to models
			commentEntities.get().stream().forEach(commentEntity -> {
				commentModels.add(new CommentModel(commentEntity));
			});
		}
		
		return commentModels;
	}
}
