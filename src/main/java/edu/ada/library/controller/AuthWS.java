package edu.ada.library.controller;

import edu.ada.library.model.dto.RegistrationModel;
import org.springframework.http.ResponseEntity;

public interface AuthWS
{
	ResponseEntity login(String email, String password);
	
	ResponseEntity register(RegistrationModel formData);
}
