package com.puzzle.cracker.exception;

/**
 * This class represents the com.puzzle.cracker.exception.FailedToEncryptPuzzleException.
 * Exception thrown when there is a failure in encrypting a puzzle.
 */
public class FailedToEncryptPuzzleException extends Exception {

	/**
	 * Constructs a new com.puzzle.cracker.exception.FailedToEncryptPuzzleException with null as its detail message.
	 */
	public FailedToEncryptPuzzleException() {
		super();
	}

	/**
	 * Constructs a new com.puzzle.cracker.exception.FailedToEncryptPuzzleException with the specified detail message.
	 *
	 * @param message the detail message (which is saved for later retrieval by the getMessage() method)
	 */
	public FailedToEncryptPuzzleException(String message) {
		super(message);
	}
}
