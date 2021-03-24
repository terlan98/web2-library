package edu.ada.library.model.entity;

import edu.ada.library.model.dto.RegistrationModel;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity @Data
@NoArgsConstructor
@Table(name = "users")
public class UserEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String birthday;
	
	private String email;
	
	private String password;
	
	private String token;
	
	@OneToMany(targetEntity = LoanEntity.class, mappedBy = "user", cascade = CascadeType.ALL)
	private List<LoanEntity> loans;
	
	public UserEntity(RegistrationModel registrationModel)
	{
		this.firstName = registrationModel.getFirstName();
		this.lastName = registrationModel.getLastName();
		this.birthday = registrationModel.getBirthday();
		this.email = registrationModel.getEmail();
		this.password = registrationModel.getPassword();
	}
}
