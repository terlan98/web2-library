package edu.ada.library.controller.impl;

import edu.ada.library.controller.AuthWS;
import edu.ada.library.exception.UserAlreadyRegisteredException;
import edu.ada.library.exception.UserNotFoundException;
import edu.ada.library.exception.WrongPasswordException;
import edu.ada.library.model.dto.RegistrationModel;
import edu.ada.library.model.entity.UserEntity;
import edu.ada.library.service.AuthService;
import edu.ada.library.service.TokenService;
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
		log.info("Login Email :: {}", email);
		log.info("Login Password :: {}", password);
		
		try
		{
			UserEntity user = authService.login(email, password);
			return ResponseEntity.ok("You have been successfully authorized.\nYour token: " + user.getToken());
			
		} catch (UserNotFoundException e)
		{
			return ResponseEntity.notFound().build();
		} catch (WrongPasswordException e)
		{
			return new ResponseEntity(HttpStatus.FORBIDDEN);
		}
		
	}
	
	@Override
	@PostMapping("/register")
	public ResponseEntity register(
			@RequestBody RegistrationModel formData)
	{
		log.info("Form :: {}", formData);
		
		try
		{
			UserEntity user = authService.registration(formData);
			return new ResponseEntity(user.getToken(), HttpStatus.CREATED);
		} catch (UserAlreadyRegisteredException e)
		{
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
