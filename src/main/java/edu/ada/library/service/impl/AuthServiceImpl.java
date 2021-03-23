package edu.ada.library.service.impl;

import edu.ada.library.controller.impl.AuthWSImpl;
import edu.ada.library.model.dto.RegistrationModel;
import edu.ada.library.model.entity.UserEntity;
import edu.ada.library.repository.UserRepository;
import edu.ada.library.service.AuthService;
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
	
	@Override
	public boolean registration(RegistrationModel registrationModel)
	{
		try
		{
			userRepository.save(new UserEntity(registrationModel));
			return true;
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return false;
		}
	}
	
	@Override
	public int login(String email, String password)
	{
		UserEntity user;
		
		user = userRepository.findFirstByEmail(email);
		
		if(user != null && user.getId() > 0) // user found via email, checking email + pass
		{
			user = null;
			user = userRepository.findFirstByEmailAndPassword(email, password);
			
			if(user != null && user.getId() > 0)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
		else
		{
			return -1;
		}
	}
}
