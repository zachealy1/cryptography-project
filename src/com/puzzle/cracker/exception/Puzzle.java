package com.puzzle.cracker.exception;

import com.puzzle.cracker.crypto.CryptoLib;

import javax.crypto.SecretKey;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

/**
 * The com.puzzle.cracker.exception.Puzzle class represents a puzzle used in the Merkle's puzzles scheme.
 */
public class Puzzle {
	private SecretKey secretKey; // The secret key
	private int puzzleNumber; // The puzzle number
	private byte[] puzzleNumberAsBytes; // The puzzle number as bytes
	private byte[] byteKey; // The secret key as bytes
	private byte[] padding = new byte[16]; // The padding for the cipher

	/**
	 * Constructs a com.puzzle.cracker.exception.Puzzle object with the given puzzle number and secret key.
	 *
	 * @param puzzleNumber the puzzle number
	 * @param secretKey    the secret key
	 */
	public Puzzle(int puzzleNumber, SecretKey secretKey) {
		setPuzzleNumber(puzzleNumber); // Set the puzzle number
		setKey(secretKey); // Set the secret key
		setByteKey(secretKey.getEncoded()); // Set the secret key as bytes
		setPuzzleNumberAsBytes(CryptoLib.smallIntToByteArray(puzzleNumber)); // Set the puzzle number as bytes
	}

	/**
	 * Retrieves the puzzle as bytes.
	 *
	 * @return the puzzle as bytes
	 * @throws FailedToWriteBytesException if an error occurs while writing the puzzle as bytes
	 */
	public byte[] getPuzzleAsBytes() throws FailedToWriteBytesException {
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			outputStream.write(getPadding()); // Write padding bytes
			outputStream.write(getPuzzleNumberAsBytes()); // Write puzzle number as bytes
			outputStream.write(getByteKey()); // Write secret key as bytes
			return outputStream.toByteArray(); // Return the puzzle as bytes
		} catch (Exception e) {
			// Throw an exception if an error occurs
			throw new FailedToWriteBytesException("Error: Failed to write puzzle as bytes");
		}
	}

	/**
	 * Retrieves the puzzle number.
	 *
	 * @return the puzzle number
	 */
	public int getPuzzleNumber() {
		return puzzleNumber;
	}

	/**
	 * Sets the puzzle number.
	 *
	 * @param puzzleNumber the puzzle number to set
	 */
	public void setPuzzleNumber(int puzzleNumber) {
		this.puzzleNumber = puzzleNumber;
	}

	/**
	 * Retrieves the puzzle number as bytes.
	 *
	 * @return the puzzle number as bytes
	 */
	public byte[] getPuzzleNumberAsBytes() {
		return puzzleNumberAsBytes;
	}

	/**
	 * Sets the puzzle number as bytes.
	 *
	 * @param puzzleNumberAsBytes the puzzle number as bytes to set
	 */
	public void setPuzzleNumberAsBytes(byte[] puzzleNumberAsBytes) {
		this.puzzleNumberAsBytes = puzzleNumberAsBytes;
	}

	/**
	 * Retrieves the secret key as bytes.
	 *
	 * @return the secret key as bytes
	 */
	public byte[] getByteKey() {
		return byteKey;
	}

	/**
	 * Sets the secret key as bytes.
	 *
	 * @param byteKey the secret key as bytes to set
	 */
	public void setByteKey(byte[] byteKey) {
		this.byteKey = byteKey;
	}

	/**
	 * Retrieves the padding bytes.
	 *
	 * @return the padding bytes
	 */
	public byte[] getPadding() {
		return padding;
	}

	/**
	 * Sets the padding bytes.
	 *
	 * @param padding the padding bytes to set
	 */
	public void setPadding(byte[] padding) {
		this.padding = padding;
	}

	/**
	 * Retrieves the secret key.
	 *
	 * @return the secret key
	 */
	public SecretKey getKey() {
		return secretKey;
	}

	/**
	 * Sets the secret key.
	 *
	 * @param secretKey the secret key
	 */
	public void setKey(SecretKey secretKey) {
		this.secretKey = secretKey;
	}

	/**
	 * Returns a string representation of the com.puzzle.cracker.exception.Puzzle object.
	 * The string includes the puzzle number and the secret key.
	 *
	 * @return a string representation of the com.puzzle.cracker.exception.Puzzle object
	 */
	@Override
	public String toString() {
		// Construct and return the string representation
		return "com.puzzle.cracker.exception.Puzzle number = " + getPuzzleNumber() + ", Secret key = " + Arrays.toString(getByteKey());
	}
}

