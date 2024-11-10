# Cryptography Puzzle Project

This project demonstrates cryptographic concepts by creating, managing, and cracking encrypted puzzles. It leverages DES encryption and includes utilities for puzzle generation, decryption, and custom error handling.

## Project Structure

The project is organised into packages, each containing classes focusing on particular aspects of the puzzle encryption and decryption process.

### Packages and Key Classes

1. **Main Application (`com.puzzle.cracker.main`)**
    - **Main.java**: The main entry point for the application.
    - **PuzzleManager.java**: Manages puzzle-related operations such as creating and cracking puzzles.

2. **Crypto Library (`com.puzzle.cracker.crypto`)**
    - **CryptoLib.java**: Provides utility methods for encryption, decryption, and key generation.
    - **Constants.java**: Defines constant values used across the cryptographic operations.

3. **Puzzle Management (`com.puzzle.cracker.puzzle`)**
    - **Puzzle.java**: Represents a puzzle with encrypted data and associated keys.
    - **PuzzleCreator.java**: Handles the creation of new puzzles, including generating encrypted puzzle data.
    - **PuzzleCracker.java**: Implements logic to crack puzzles by brute-forcing possible keys and attempting decryption.

4. **Custom Exceptions (`com.puzzle.cracker.exception`)**
    - **FailedToEncryptPuzzleException.java**: Handles errors related to encryption failures during puzzle creation.
    - **FailedToWriteBytesException.java**: Thrown when byte data fails to write to output.
    - **FailedToWriteToFileException.java**: Manages file-writing errors.
    - **KeyNotFoundException.java**: Thrown when a required decryption key is missing.

---

## Features

- **Puzzle Creation and Encryption**: Generates puzzles encrypted with DES and stores data for later decryption attempts.
- **Puzzle Cracking**: Attempts to crack the puzzle by brute-forcing keys, using `PuzzleCracker` to validate the correct decryption.
- **Error Handling**: Custom exceptions provide meaningful error messages and manage specific failure cases in cryptographic operations.
- **Utility Functions**: `CryptoLib` supplies helper functions for encryption, decryption, and key management.

## Getting Started

### Prerequisites

- Java 8 or later
- Maven for dependency management and building

### Setup

1. Clone the repository to your local machine.
2. Navigate to the project directory and build the project with Maven:

   ```bash
   mvn clean install
    ```
3. Run the project in your IDE or from the command line:

   ```bash
   java -jar target/cryptography-puzzle-project.jar
   ```
### Usage

- **Create and Encrypt Puzzles:** Use the `PuzzleCreator` class to generate encrypted puzzles.
- **Crack Puzzles:** Leverage the `PuzzleCracker` class to attempt the decryption of a puzzle by brute-forcing possible keys.
- **Error Handling:** Manage exceptions related to encryption and file handling using custom exception classes.

