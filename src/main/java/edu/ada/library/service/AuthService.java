package edu.ada.library.service;

import edu.ada.library.exception.UserAlreadyRegisteredException;
import edu.ada.library.exception.UserNotFoundException;
import edu.ada.library.exception.WrongPasswordException;
import edu.ada.library.model.dto.RegistrationModel;
import edu.ada.library.model.entity.UserEntity;

public interface AuthService
{
	UserEntity registration(RegistrationModel registrationModel) throws UserAlreadyRegisteredException;
	
	UserEntity login(String email, String password) throws UserNotFoundException, WrongPasswordException;
	
}
