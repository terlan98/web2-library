package edu.ada.library.repository;

import edu.ada.library.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>
{
	UserEntity findFirstByEmailAndPassword(String email, String password);
	UserEntity findFirstByEmail(String email);
	UserEntity findFirstByToken(String token);
}
