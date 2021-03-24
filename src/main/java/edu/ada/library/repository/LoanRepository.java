package edu.ada.library.repository;

import edu.ada.library.model.entity.BookEntity;
import edu.ada.library.model.entity.LoanEntity;
import edu.ada.library.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface LoanRepository extends CrudRepository<LoanEntity, Long>
{
	LoanEntity findFirstByBook(BookEntity book);
}
