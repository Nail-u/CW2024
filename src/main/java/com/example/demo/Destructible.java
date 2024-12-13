package com.example.demo;

/**
 * Interface for objects that can take damage and be destroyed.
 */
public interface Destructible {

	/**
	 * Method to take damage.
	 */
	void takeDamage();

	/**
	 * Method to destroy an object.
	 */
	void destroy();
	
}
