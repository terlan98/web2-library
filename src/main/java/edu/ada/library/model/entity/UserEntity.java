package edu.ada.library.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.ada.library.model.dto.RegistrationModel;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
	
	@JsonIgnore
	private String password;
	
	@JsonIgnore
	private String token;
	
	@OneToMany(targetEntity = LoanEntity.class, mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<LoanEntity> loans;
	
	@OneToMany(targetEntity = CourseEntity.class)
	private Set<CourseEntity> courses;
	
	public UserEntity(RegistrationModel registrationModel)
	{
		this.firstName = registrationModel.getFirstName();
		this.lastName = registrationModel.getLastName();
		this.birthday = registrationModel.getBirthday();
		this.email = registrationModel.getEmail();
		this.password = registrationModel.getPassword();
	}
}
