package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FindAndReplace {

	public static void main(String[] args) {

//		Write a program that can be used to replace all occurrences of one word with another word.
//
//
//		The program should prompt the user for the following values:
//		•The search word
//		•The word to replace the search word with
//		•The source file
//		This must be an existing file. If the user enters an invalid source file, the program indicates this and exits.
//		•The destination file.
//		The program creates a copy of the source file with the requested replacements at this location. If the file already exists, it should be overwritten. If the user enters an invalid destination file, the program indicates this and exits.

		// alices_adventures_in_wonderland.txt

		// create scanner for user input
		Scanner userInput = new Scanner(System.in);

		System.out.println("What is the file that should be searched?");
		String inputPath = userInput.nextLine();

		// create file object with filepath user inputs
		File inputFile = new File(inputPath);
		// if file doesn't exist, exits program
		if (!inputFile.exists()) {

			System.out.println("Invalid file. Exiting program.");
			System.exit(1);
		}

		// prompt user for word to find and stores in targetWord
		System.out.println("What is the search word you are looking for (case sensitive)?");
		String targetWord = userInput.nextLine();

		// prompt user for replacement word and store in replacementWord
		System.out.println("What is the word you are replacing " + targetWord + " with?");
		String replacementWord = userInput.nextLine();

		// prompt user for new filepath to write changes to
		System.out.println("What is the file that changes should be saved to?");
		String outputPath = userInput.nextLine();
		// create file object with user input
		File outputFile = new File(outputPath);

		// make a string to temporarily store edits
		String buffer = "";
		// make scanner object to read inputFile
		try (Scanner fileScanner = new Scanner(inputFile.getAbsoluteFile())) {
			// read file line by line
			while (fileScanner.hasNextLine()) {

				// make String object of each line
				String currentLine = fileScanner.nextLine();
				// if blank line, stores blank line in buffer
				if (currentLine.length() == 0) {
					buffer = buffer + "\n";
					continue;
				}
				// store strings inside an array, split at spaces.
				String[] lineArr = currentLine.split(" ");
				int counter = 0;

				// iterates through array
				while (counter < lineArr.length) {
					// create temporary string to compare to target string
					// using array[counter]
					String word = lineArr[counter];
					// if strings match
					if (word.equals(targetWord)) {
						// replaces word in array with replacement word
						lineArr[counter] = replacementWord;
						// increments counter
						counter++;
						// if string doesn't match
					} else {
						// increments counter without change
						counter++;
					}
				}
				// 
				for (int i = 0; i < lineArr.length; i++) {
					buffer = buffer + lineArr[i] + " ";
				}

				buffer = buffer.trim() + "\n";

			}
			System.out.println(buffer);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try (PrintWriter writer = new PrintWriter(outputFile.getAbsoluteFile())) {
			writer.write(buffer);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
