package com.puzzle.cracker.exception;

/**
 * Represents the com.puzzle.cracker.exception.KeyNotFoundException.
 * Exception thrown when a key is not found.
 */
public class KeyNotFoundException extends Exception {

	/**
	 * Constructs a new com.puzzle.cracker.exception.KeyNotFoundException with null as its detail message.
	 */
	public KeyNotFoundException() {
		super();
	}

	/**
	 * Constructs a new com.puzzle.cracker.exception.KeyNotFoundException with the specified detail message.
	 *
	 * @param message the detail message (which is saved for later retrieval by the getMessage() method)
	 */
	public KeyNotFoundException(String message) {
		super(message);
	}
}

