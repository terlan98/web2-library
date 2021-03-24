package edu.ada.library.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.ada.library.model.entity.BookEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

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
	
	public BookModel(BookEntity book)
	{
		this.id = book.getId();
		this.name = book.getName();
		this.author = book.getAuthor();
		this.category = book.getCategory();
		this.publishedOn = book.getPublishedOn();
	}
}
