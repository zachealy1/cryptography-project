package com.puzzle.cracker.exception;

/**
 * Represents the com.puzzle.cracker.exception.FailedToWriteToFileException.
 * Exception thrown when there is a failure in writing to a file.
 */
public class FailedToWriteToFileException extends Exception {

	/**
	 * Constructs a new com.puzzle.cracker.exception.FailedToWriteToFileException with null as its detail message.
	 */
	public FailedToWriteToFileException() {
		super();
	}

	/**
	 * Constructs a new com.puzzle.cracker.exception.FailedToWriteToFileException with the specified detail message.
	 *
	 * @param message the detail message (which is saved for later retrieval by the getMessage() method)
	 */
	public FailedToWriteToFileException(String message) {
		super(message);
	}
}

