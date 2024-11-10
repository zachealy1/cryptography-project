package com.puzzle.cracker.main;

import com.puzzle.cracker.exception.Puzzle;
import java.util.ArrayList;

/**
 * The com.puzzle.cracker.main.PuzzleManager class manages a collection of puzzles.
 */
public class PuzzleManager {

	private static PuzzleManager puzzleManagerInstance = null; // The singleton instance of com.puzzle.cracker.main.PuzzleManager
	private ArrayList<Puzzle> puzzles = new ArrayList<>(); // The list of puzzles

	/**
	 * Private constructor to prevent instantiation from outside the class.
	 */
	private PuzzleManager() {
	}

	/**
	 * Returns the singleton instance of com.puzzle.cracker.main.PuzzleManager.
	 *
	 * @return the com.puzzle.cracker.main.PuzzleManager instance
	 */
	public static PuzzleManager getInstance() {
		if (puzzleManagerInstance == null) {
			puzzleManagerInstance = new PuzzleManager();
		}
		return puzzleManagerInstance;
	}

	/**
	 * Adds a puzzle to the collection.
	 *
	 * @param puzzle the puzzle to add
	 */
	public void addPuzzle(Puzzle puzzle) {
		puzzles.add(puzzle);
	}

	/**
	 * Removes a puzzle from the collection.
	 *
	 * @param puzzle the puzzle to remove
	 */
	public void removePuzzle(Puzzle puzzle) {
		puzzles.remove(puzzle);
	}

	/**
	 * Clears all puzzles from the collection.
	 */
	public void clearPuzzles() {
		puzzles.clear();
	}

	/**
	 * Returns the list of puzzles.
	 *
	 * @return the list of puzzles
	 */
	public ArrayList<Puzzle> getPuzzles() {
		return puzzles;
	}

	/**
	 * Sets the list of puzzles.
	 *
	 * @param puzzles the list of puzzles to set
	 */
	public void setPuzzles(ArrayList<Puzzle> puzzles) {
		this.puzzles = puzzles;
	}

	/**
	 * Prints all puzzles in the collection.
	 * Each puzzle is printed using its toString() representation.
	 */
	public void printAllPuzzles() {
		// Iterate through each puzzle in the collection
		for (Puzzle puzzle : puzzles) {
			// Print the string representation of the puzzle
			System.out.println(puzzle);
		}
	}
}

