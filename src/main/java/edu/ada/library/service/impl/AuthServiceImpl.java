package edu.ada.library.service.impl;

import edu.ada.library.exception.UserAlreadyRegisteredException;
import edu.ada.library.exception.UserNotFoundException;
import edu.ada.library.exception.WrongPasswordException;
import edu.ada.library.model.dto.RegistrationModel;
import edu.ada.library.model.entity.UserEntity;
import edu.ada.library.repository.UserRepository;
import edu.ada.library.service.AuthService;
import edu.ada.library.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService
{
	protected static Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TokenService tokenService;
	
	@Override
	public UserEntity registration(RegistrationModel registrationModel) throws UserAlreadyRegisteredException
	{
		if (userRepository.findFirstByEmail(registrationModel.getEmail()) != null) // user already exists
		{
			log.info("User already exists");
			throw new UserAlreadyRegisteredException();
		}
		
		UserEntity user = new UserEntity(registrationModel);
		user.setToken(tokenService.generate());
		userRepository.save(user);
		return user;
	}
	
	@Override
	public UserEntity login(String email, String password) throws UserNotFoundException, WrongPasswordException
	{
		UserEntity user;
		
		user = userRepository.findFirstByEmail(email);
		
		if (user != null && user.getId() > 0) // user found via email, checking email + pass
		{
			user = userRepository.findFirstByEmailAndPassword(email, password);
			
			if (user != null && user.getId() > 0)
			{
				log.info("Login successful");
			}
			else
			{
				log.info("Wrong password");
				throw new WrongPasswordException("Wrong password during login");
			}
		}
		else
		{
			throw new UserNotFoundException("Can't find the user during login");
		}
		
		return user;
	}
	
	@Override
	public UserEntity findByToken(String token) throws UserNotFoundException
	{
		UserEntity user = userRepository.findFirstByToken(token);
		if (user == null) throw new UserNotFoundException("Can't find any user with the given token");
		return user;
	}
}
