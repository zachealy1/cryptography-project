package com.puzzle.cracker.puzzle;

import com.puzzle.cracker.crypto.CryptoLib;
import com.puzzle.cracker.exception.Puzzle;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class PuzzleCracker {
	private final ArrayList<byte[]> puzzleData = new ArrayList<>();
	private Puzzle puzzle = null;

	public PuzzleCracker(String filePath) {
		this.readPuzzlesFromFile(filePath);
	}

	private void readPuzzlesFromFile(String filePath) {
		try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
			while (fileInputStream.available() >= 32) {
				byte[] data = new byte[32];
				fileInputStream.read(data);
				this.puzzleData.add(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Puzzle crack(int puzzleIndex) {
		Puzzle crackedPuzzle;

		for (int i = 0; i < 65536; i++) {
			try {
				byte[] keyBytes = this.createKeyFromInt(i);
				SecretKey key = CryptoLib.createKey(keyBytes);
				byte[] decryptedData = this.tryDecrypt(key, this.puzzleData.get(puzzleIndex));

				if (decryptedData != null) {
					byte[] expectedPrefix = new byte[16];
					byte[] actualPrefix = Arrays.copyOfRange(decryptedData, 0, 16);

					if (Arrays.equals(expectedPrefix, actualPrefix)) {
						byte[] puzzleNumberBytes = Arrays.copyOfRange(decryptedData, 16, 18);
						int puzzleNumber = CryptoLib.byteArrayToSmallInt(puzzleNumberBytes);
						byte[] secretKeyBytes = Arrays.copyOfRange(decryptedData, 18, 26);
						SecretKey secretKey = CryptoLib.createKey(secretKeyBytes);

						crackedPuzzle = new Puzzle(puzzleNumber, secretKey);
						this.puzzle = crackedPuzzle;
						return crackedPuzzle;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	private byte[] createKeyFromInt(int value) {
		byte[] shortKeyBytes = CryptoLib.smallIntToByteArray(value);
		byte[] keyBytes = new byte[8];
		keyBytes[0] = shortKeyBytes[0];
		keyBytes[1] = shortKeyBytes[1];
		return keyBytes;
	}

	private byte[] tryDecrypt(SecretKey key, byte[] data) {
		try {
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			return cipher.doFinal(data);
		} catch (BadPaddingException e) {
			return null; // Incorrect padding indicates decryption failure
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void decryptMessage(byte[] encryptedMessage) {
		try {
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, this.puzzle.getKey());
			byte[] decryptedMessage = cipher.doFinal(encryptedMessage);
			System.out.println("Bob: Received message and decrypted: " + new String(decryptedMessage));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
