package edu.ada.library.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.ada.library.model.entity.BookEntity;
import edu.ada.library.model.entity.LoanEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class BookModel implements Serializable
{
	private Long id;
	private String name;
	private String author;
	private String category;
	
	@JsonFormat(pattern = "dd.MM.yyyy")
	@Temporal(TemporalType.DATE)
	private Date publishedOn;
	
	private Long takerUserId;
	
	
	private List<CommentModel> comments;
	
	public BookModel(BookEntity book)
	{
		this.id = book.getId();
		this.name = book.getName();
		this.author = book.getAuthor();
		this.category = book.getCategory();
		this.publishedOn = book.getPublishedOn();
		
		LoanEntity loanEntity = book.getLoans().stream().filter(x -> x.isReturned() == false).findFirst().orElse(null);
		if (loanEntity != null)
		{
			this.takerUserId = loanEntity.getUser().getId();
		}
	}
	
	public void setComments(List<CommentModel> comments)
	{
		// TODO: Finish this function
		this.comments = comments;
	}
}
