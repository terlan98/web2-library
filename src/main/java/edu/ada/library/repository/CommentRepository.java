package edu.ada.library.repository;

import edu.ada.library.model.entity.CommentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends MongoRepository<CommentEntity, String>
{
	Optional<List<CommentEntity>> findAllByBookId(Long bookId);
}
