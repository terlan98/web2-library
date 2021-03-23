package edu.ada.library.service;

import edu.ada.library.model.dto.RegistrationModel;

public interface AuthService
{
	boolean registration(RegistrationModel registrationModel);
	
	int login(String email, String password);
	
}
