package edu.ada.library.repository;

import edu.ada.library.model.entity.BookEntity;
import edu.ada.library.model.entity.LoanEntity;
import edu.ada.library.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LoanRepository extends CrudRepository<LoanEntity, Long>
{
	LoanEntity findFirstByBook(BookEntity book);
	List<LoanEntity> findAllByUser(UserEntity user);
}
