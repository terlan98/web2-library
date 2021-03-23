package edu.ada.library.model.entity;

import edu.ada.library.model.dto.RegistrationModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "users")
public class UserEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String birthday;
	
	private String email;
	
	private String password;
	
	private String token;
	
	public UserEntity()
	{
	}
	
	public UserEntity(RegistrationModel registrationModel)
	{
		this.firstName = registrationModel.getFirstName();
		this.lastName = registrationModel.getLastName();
		this.birthday = registrationModel.getBirthday();
		this.email = registrationModel.getEmail();
		this.password = registrationModel.getPassword();
	}
}
