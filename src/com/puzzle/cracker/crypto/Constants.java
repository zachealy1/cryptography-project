package com.puzzle.cracker.crypto;

/**
 * This class defines constants used in a puzzle-solving application.
 * It includes various parameters such as the number of puzzles, the filename,
 * and secret messages used in the application.
 */
public class Constants {

	/**
	 * The number of puzzles in the application.
	 */
	public static final int NUMBER_OF_PUZZLES = 4096;

	/**
	 * The number of populated bytes used in the puzzles.
	 */
	public static final int NUMBER_OF_POPULATED_BYTES = 2;

	/**
	 * The filename used to store puzzles.
	 */
	public static final String FILENAME = "puzzles.bin";

	/**
	 * The first secret message used in the application.
	 */
	public static final String SECRET_MESSAGE_ONE = "Testing Merkle’s Puzzles!";

	/**
	 * The second secret message used in the application.
	 */
	public static final String SECRET_MESSAGE_TWO = "This is a secret message!";

	/**
	 * The third secret message used in the application.
	 */
	public static final String SECRET_MESSAGE_THREE = "These are not the droids you are looking for.";
}

