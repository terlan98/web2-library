package edu.ada.library.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "comments")
@Data
@NoArgsConstructor
public class CommentEntity
{
	@Id
	private String id;
	private Long bookId;
	private String authorName;
	private String content;
	
	private List<CommentEntity> replies = new ArrayList<>(1);
	
	public CommentEntity(String authorName, Long bookId, String content)
	{
		this.authorName = authorName;
		this.bookId = bookId;
		this.content = content;
	}
}
