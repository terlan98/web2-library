package edu.ada.library.service;

/**
 * Interface to be implemented by classes offering token services.
 */
public interface TokenService
{
	/**
	 * Generates a new token
	 * @return
	 */
	String generate();
}
