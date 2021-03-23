package edu.ada.library.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class RegistrationModel implements Serializable
{
	private String firstName;
	private String lastName;
	private String birthday;
	private String email;
	private String password;
}
