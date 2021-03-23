package edu.ada.library.service.impl;

import edu.ada.library.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TokenServiceImpl implements TokenService
{
	@Override
	public String generate()
	{
		Random r = new Random();
		StringBuffer buffer = new StringBuffer();
		final int tokenLength = 50;
		
		while(buffer.length() < tokenLength){
			buffer.append(Integer.toHexString(r.nextInt()));
		}
		
		return buffer.toString().substring(0, tokenLength);
	}
}
