package edu.ada.library.exception;

public class WrongPasswordException extends Exception
{
	public WrongPasswordException(String message)
	{
		super(message);
	}
}
