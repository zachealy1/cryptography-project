package com.puzzle.cracker.main;

import com.puzzle.cracker.crypto.Constants;
import com.puzzle.cracker.exception.Puzzle;
import com.puzzle.cracker.puzzle.PuzzleCracker;
import com.puzzle.cracker.puzzle.PuzzleCreator;

import java.util.Arrays;

/**
 * The com.puzzle.cracker.main.Main class contains the entry point of the program and methods for testing the program functionality.
 */
public class Main {

	/**
	 * Tests the program functionality with a specific puzzle number, message, and file name.
	 *
	 * @param puzzleIncrement the puzzle increment to use for testing
	 * @param message         the secret message to encryptData and decrypt
	 * @param fileName        the name of the file to store encrypted puzzles
	 * @throws Exception if an error occurs during testing
	 */
	public static void testProgram(int puzzleIncrement, String message, String fileName) throws Exception {
		PuzzleCreator puzzleCreator = new PuzzleCreator();

		// Encrypt puzzle to file
		System.out.println("--------Encrypting com.puzzle.cracker.exception.Puzzle To File--------");
		puzzleCreator.encryptPuzzleToFile(fileName);
		System.out.println("Done");
		System.out.println();

		// Instantiate com.puzzle.cracker.puzzle.PuzzleCracker
		System.out.println("--------Instantiating com.puzzle.cracker.puzzle.PuzzleCracker--------");
		PuzzleCracker puzzleCracker = new PuzzleCracker(fileName);
		System.out.println("Done");
		System.out.println();

		// Crack puzzle
		System.out.println("--------Cracking com.puzzle.cracker.exception.Puzzle--------");
		Puzzle crackedPuzzle = puzzleCracker.crack(puzzleIncrement);
		System.out.println("Cracked com.puzzle.cracker.exception.Puzzle: " + crackedPuzzle);
		System.out.println();

		// Encrypt message
		System.out.println("--------Encrypting Message--------");
		System.out.println("Encrypted Message: " + message);
		System.out.println("Encrypted Message Key: " + Arrays.toString(puzzleCreator.findKey(crackedPuzzle.getPuzzleNumber()).getEncoded()));
		byte[] encryptedMessage = puzzleCreator.encryptData(puzzleCreator.findKey(crackedPuzzle.getPuzzleNumber()).getEncoded(), message.getBytes());
		System.out.println("Encrypted Message: " + Arrays.toString(encryptedMessage));
		System.out.println();

		// Decrypt message
		System.out.println("--------Decrypting Message--------");
		puzzleCracker.decryptMessage(encryptedMessage);
		System.out.println();
	}

	/**
	 * The entry point of the program.
	 *
	 * @param args command-line arguments
	 * @throws Exception if an error occurs during execution
	 */
	public static void main(String[] args) throws Exception {

		// Test program with specific parameters
		testProgram(5, Constants.SECRET_MESSAGE_ONE, Constants.FILENAME);

		// Uncomment the following block to test program with all puzzles
		//		for (int i = 0; i <= com.puzzle.cracker.crypto.Constants.NUMBER_OF_PUZZLES; i++) {
		//			testProgram(i, com.puzzle.cracker.crypto.Constants.SECRET_MESSAGE_ONE, com.puzzle.cracker.crypto.Constants.FILENAME);
		//		}
		//		System.out.println("Testing Complete!");
	}
}
