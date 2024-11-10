package com.puzzle.cracker.puzzle;

import com.puzzle.cracker.crypto.Constants;
import com.puzzle.cracker.crypto.CryptoLib;
import com.puzzle.cracker.exception.FailedToEncryptPuzzleException;
import com.puzzle.cracker.exception.FailedToWriteToFileException;
import com.puzzle.cracker.exception.KeyNotFoundException;
import com.puzzle.cracker.exception.Puzzle;
import com.puzzle.cracker.main.PuzzleManager;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The com.puzzle.cracker.puzzle.PuzzleCreator class handles the creation and encryption of puzzles.
 */
public class PuzzleCreator {

	/**
	 * Constructs a com.puzzle.cracker.puzzle.PuzzleCreator object.
	 */
	public PuzzleCreator() {
	}

	/**
	 * Creates a set of puzzles.
	 *
	 * @return the list of created puzzles
	 */
	public ArrayList<Puzzle> createPuzzles() throws NoSuchAlgorithmException {
		// Clear existing puzzles
		PuzzleManager.getInstance().clearPuzzles();
		// Loop to create a specified number of puzzles
		for (int i = 0; i < Constants.NUMBER_OF_PUZZLES; i++) {
			// Generate a random DES key and create a new com.puzzle.cracker.exception.Puzzle object with an ID and the generated key
			PuzzleManager.getInstance().addPuzzle(new Puzzle(i + 1, generateRandomDesKey()));
		}
		// Return the list of created puzzles
		return PuzzleManager.getInstance().getPuzzles();
	}

	/**
	 * Creates a random key.
	 *
	 * @return the randomly generated key
	 */
	public byte[] createRandomKey() {
		SecureRandom randomNumber = new SecureRandom(); // SecureRandom instance for generating random bytes
		byte[] keyBytes = new byte[8]; // Byte array to store the random key

		for (int i = 0; i < Constants.NUMBER_OF_POPULATED_BYTES; i++) {
			// Populate the keyBytes array with random bytes
			keyBytes[i] = (byte) (randomNumber.nextInt(256) - 128); // Generate a random byte between -128 and 127
		}
		return keyBytes; // Return the generated random key
	}

	/**
	 * Generates a random DES (Data Encryption Standard) key.
	 *
	 * @return the generated SecretKey
	 * @throws NoSuchAlgorithmException if DES algorithm is not available
	 */
	public static SecretKey generateRandomDesKey() throws NoSuchAlgorithmException {
		// Create a KeyGenerator instance for DES algorithm
		KeyGenerator keyGen = KeyGenerator.getInstance("DES");
		// Initialize the KeyGenerator with a key size of 56 bits
		keyGen.init(56);
		// Generate and return the DES key
		return keyGen.generateKey();
	}

	/**
	 * Encrypts a puzzle using the provided key.
	 *
	 * @param key    the key used for encryption
	 * @param puzzle the puzzle to be encrypted
	 * @return the encrypted puzzle
	 * @throws FailedToEncryptPuzzleException if encryption fails
	 */
	public byte[] encryptPuzzle(byte[] key, Puzzle puzzle) throws FailedToEncryptPuzzleException {
		byte[] encryptedPuzzle;
		try {
			// Attempt to encryptData the plain text using the puzzle key
			encryptedPuzzle = encryptData(key, puzzle.getPuzzleAsBytes());
		} catch (Exception e) {
			// If encryption fails, throw a com.puzzle.cracker.exception.FailedToEncryptPuzzleException
			throw new FailedToEncryptPuzzleException("Failed to encryptData puzzle");
		}
		return encryptedPuzzle; // Return the encrypted puzzle
	}


	/**
	 * Encrypts data using the DES (Data Encryption Standard) algorithm.
	 *
	 * @param key  the key used for encryption
	 * @param data the data to be encrypted
	 * @return the encrypted data
	 * @throws Exception if encryption fails
	 */
	public byte[] encryptData(byte[] key, byte[] data) throws Exception {
		// Create a Cipher instance for DES algorithm
		Cipher cipher = Cipher.getInstance("DES");
		// Initialize the Cipher in encryption mode with the provided key
		cipher.init(Cipher.ENCRYPT_MODE, CryptoLib.createKey(key));
		// Perform encryption and return the result
		return cipher.doFinal(data);
	}

	/**
	 * Encrypts puzzles and writes them to a file.
	 *
	 * @param filename the name of the file to write the encrypted puzzles to
	 * @throws NoSuchAlgorithmException     if the requested cryptographic algorithm is not available
	 * @throws FailedToWriteToFileException if writing to file fails
	 * @throws FileNotFoundException        if the file is not found
	 */
	public void encryptPuzzleToFile(
			String filename) throws NoSuchAlgorithmException, FailedToWriteToFileException, FileNotFoundException {
		// Create a set of puzzles
		ArrayList<Puzzle> puzzles = createPuzzles();
		// Shuffle the puzzles
		Collections.shuffle(puzzles);
		// Initialize a FileOutputStream to write to the specified file
		FileOutputStream fileOutputStream = new FileOutputStream(filename);
		// Iterate over the puzzle set
		for (Puzzle puzzle : puzzles) {
			try {
				// Encrypt the puzzle and write it to the file
				fileOutputStream.write(encryptPuzzle(createRandomKey(), puzzle));
			} catch (FailedToEncryptPuzzleException | IOException e) {
				// If any exception occurs during encryption or writing, throw a com.puzzle.cracker.exception.FailedToWriteToFileException
				throw new FailedToWriteToFileException("Failed to write puzzle to file");
			}
		}
	}

	/**
	 * Finds the key corresponding to a puzzle number.
	 *
	 * @param puzzleNumber the puzzle number
	 * @return the secret key associated with the puzzle number
	 * @throws KeyNotFoundException if the key is not found
	 */
	public SecretKey findKey(int puzzleNumber) throws KeyNotFoundException {
		// Iterate over the puzzles in the com.puzzle.cracker.main.PuzzleManager
		for (int i = 0; i < PuzzleManager.getInstance().getPuzzles().size(); i++) {
			// Check if the current puzzle's number matches the specified puzzle number
			if (PuzzleManager.getInstance().getPuzzles().get(i).getPuzzleNumber() == puzzleNumber) {
				// If a match is found, return the key associated with the puzzle
				return PuzzleManager.getInstance().getPuzzles().get(i).getKey();
			}
		}
		// If no matching key is found, throw a com.puzzle.cracker.exception.KeyNotFoundException
		throw new KeyNotFoundException("com.puzzle.cracker.exception.Puzzle key not found");
	}
}
