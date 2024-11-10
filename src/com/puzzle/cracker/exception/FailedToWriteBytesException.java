package com.puzzle.cracker.exception;

/**
 * Represents the com.puzzle.cracker.exception.FailedToWriteBytesException.
 * Exception thrown when there is a failure in writing bytes.
 */
public class FailedToWriteBytesException extends Exception {

	/**
	 * Constructs a new com.puzzle.cracker.exception.FailedToWriteBytesException with null as its detail message.
	 */
	public FailedToWriteBytesException() {
		super();
	}

	/**
	 * Constructs a new com.puzzle.cracker.exception.FailedToWriteBytesException with the specified detail message.
	 *
	 * @param message the detail message (which is saved for later retrieval by the getMessage() method)
	 */
	public FailedToWriteBytesException(String message) {
		super(message);
	}
}

