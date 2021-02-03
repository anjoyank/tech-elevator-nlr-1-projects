package com.techelevator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class WordCount {

	public static void main(String[] args) {

		int numWords = 0;
		int numLines = 0;
		int numChars = 0;
		int numSentences = 0;

		Scanner myFile = null;

		Scanner userInput = new Scanner(System.in);

		System.out.println("What is the file that should be searched?");

		String path = userInput.nextLine(); // reading t he file.

		// alices_adventures_in_wonderland.txt

		try {

			Scanner file = new Scanner(new File(path));
			myFile = file;

		} catch (FileNotFoundException e) {

			System.out.println("Invalid File");
		}

		// calculation for word count.

		String curLine;

		while (myFile.hasNextLine()) {

			// Calculation for Sentence count
			curLine = myFile.nextLine();

			if (curLine.contains("!") || curLine.contains("?") || curLine.contains("."))
				numSentences++;

			int size = curLine.length();
			boolean foundDiv = true; // no word in this line.
			boolean foundChar = false; // no characters yet;

			// we want to check current line for words.
			for (int i = 0; i < size; i++) {
				if (curLine.charAt(i) == ' ') {

					foundDiv = true;
					foundChar = false;
				} else {
					foundChar = true;
					numChars++;
				}

				if (foundChar && foundDiv) {

					numWords++;
					foundDiv = false; // no devision since last char;
				}

			}

		}

		System.out.println("Number of words = " + numWords);
		System.out.println("Number of sentences = " + numSentences);

		myFile.close();

	}

}
