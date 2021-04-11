package edu.ada.library.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.ada.library.model.entity.CommentEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CommentModel implements Serializable
{
	@JsonInclude(JsonInclude.Include.NON_NULL) // JSONIgnore if id is null (occurs only when the comment is a reply)
	private String id;
	private Long bookId;
	private String authorName;
	private String content;
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY) // JSONIgnore if there are no replies
	private List<CommentModel> replies = new ArrayList<>(1);
	
	
	public CommentModel(CommentEntity commentEntity)
	{
		this.id = commentEntity.getId();
		this.bookId = commentEntity.getBookId();
		this.authorName = commentEntity.getAuthorName();
		this.content = commentEntity.getContent();
		
		if (commentEntity.getReplies() != null && !commentEntity.getReplies().isEmpty())
		{
			commentEntity.getReplies().stream().forEach(entity -> this.replies.add(new CommentModel(entity)));
		}
	}
}
