package edu.ada.library.controller;

import edu.ada.library.model.dto.RegistrationModel;
import org.springframework.http.ResponseEntity;

/**
 * Handles authentication-related requests.
 */
public interface AuthWS
{
	/**
	 * Attempts to log in the user with the given email and password. Returns the user's token if successful.
	 * @param email
	 * @param password
	 * @return an HTTP response
	 */
	ResponseEntity login(String email, String password);
	
	/**
	 * Registers the user based on the given registration model. Returns the user's token if successful.
	 * @param formData
	 * @return an HTTP response
	 */
	ResponseEntity register(RegistrationModel formData);
}
