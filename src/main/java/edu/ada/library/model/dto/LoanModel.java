package edu.ada.library.model.dto;

import edu.ada.library.model.entity.BookEntity;
import edu.ada.library.model.entity.LoanEntity;
import edu.ada.library.model.entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class LoanModel implements Serializable
{
	private Long id;
	private UserEntity user;
	private BookEntity book;
	private boolean returned;
	
	public LoanModel(LoanEntity loan)
	{
		this.id = loan.getId();
		this.user = loan.getUser();
		this.book = loan.getBook();
		this.returned = loan.isReturned();
	}
}
