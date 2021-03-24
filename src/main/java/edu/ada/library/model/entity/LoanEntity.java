package edu.ada.library.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity @Data
@NoArgsConstructor
@Table(name = "loans")
public class LoanEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(targetEntity = UserEntity.class)
	@JoinColumn()
	private UserEntity user;
	
	@ManyToOne(targetEntity = BookEntity.class)
	@JoinColumn()
	private BookEntity book;
	
	private boolean returned;
	
	public LoanEntity(UserEntity user, BookEntity book)
	{
		this.user = user;
		this.book = book;
	}
}
