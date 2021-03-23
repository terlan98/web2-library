package edu.ada.library.controller.impl;

import edu.ada.library.controller.AuthWS;
import edu.ada.library.model.dto.RegistrationModel;
import edu.ada.library.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthWSImpl implements AuthWS
{
	protected static Logger log = LoggerFactory.getLogger(AuthWSImpl.class);
	
	@Autowired
	private AuthService authService;
	
	@Override
	@GetMapping("/login")
	public ResponseEntity login(
			@RequestHeader("email") String email,
			@RequestHeader("password") String password)
	{
		log.info("Email :: {}", email);
		log.info("Password :: {}", password);
		
		int result = authService.login(email, password);
		
		if(result < 0)
		{
			return ResponseEntity.notFound().build();
		}
		else if(result == 0)
		{
			return new ResponseEntity(HttpStatus.FORBIDDEN);
		}
		else // create token here
		{
			return ResponseEntity.ok("You have been successfully authorized");
		}
	}
	
	@Override
	@GetMapping("/forget")
	public ResponseEntity forget(
			@RequestParam(name = "email", required = true) String email)
	{
		log.info("Email :: {}", email);
		return null;
	}
	
	@Override
	@PostMapping("/register")
	public ResponseEntity register(
			@RequestBody RegistrationModel formData)
	{
		log.info("Form :: {}", formData);
		if(authService.registration(formData))
		{
			return ResponseEntity.created(null).build();
		}
		else
		{
			return ResponseEntity.unprocessableEntity().build();
		}
	}
}
