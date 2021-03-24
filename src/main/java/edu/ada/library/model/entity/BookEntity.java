package edu.ada.library.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity @Data
@NoArgsConstructor
@Table(name = "books")
public class BookEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true)
	private String name;
	
	private String author;
	
	private String category;
	
	@JsonFormat(pattern = "dd.MM.yyyy")
	@Temporal(TemporalType.DATE)
	private Date publishedOn;
	
	@OneToMany(targetEntity = LoanEntity.class, mappedBy = "book", cascade = CascadeType.ALL)
	private List<LoanEntity> loans;
	
	public BookEntity(String name, String author, String category, Date publishedOn)
	{
		this.name = name;
		this.author = author;
		this.category = category;
		this.publishedOn = publishedOn;
	}
}
