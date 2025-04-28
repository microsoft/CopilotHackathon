package com.example;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        String secretWord = "hangman";
        char[] guessedWord = new char[secretWord.length()];
        Arrays.fill(guessedWord, '_');
        int incorrectGuesses = 0; // Optional: Track incorrect guesses
        final int MAX_INCORRECT_GUESSES = 6; // Optional: Limit incorrect guesses

        Scanner scanner = new Scanner(System.in);

        // Game loop: continues as long as the word isn't fully guessed
        while (String.valueOf(guessedWord).contains("_")) {
            // Print the current state of the guessed word
            System.out.print("Word: ");
            for (int i = 0; i < guessedWord.length; i++) {
                System.out.print(guessedWord[i] + (i == guessedWord.length - 1 ? "" : " "));
            }
            System.out.println(); // New line after printing the word

            System.out.print("Guess a letter: ");
            String input = scanner.nextLine().toLowerCase(); // Read input and convert to lowercase

            // Basic input validation (check if it's a single letter)
            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Please enter a single letter.");
                continue; // Ask for input again
            }

            char guess = input.charAt(0);
            boolean correctGuess = false;

            // Check if the guessed letter is in the secret word
            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.charAt(i) == guess && guessedWord[i] == '_') {
                    guessedWord[i] = guess;
                    correctGuess = true;
                }
            }

            if (!correctGuess) {
                incorrectGuesses++; // Increment incorrect guesses if the letter wasn't found
                System.out.println("Incorrect guess.");
                // Optional: Add logic for losing the game based on incorrectGuesses
                // if (incorrectGuesses >= MAX_INCORRECT_GUESSES) {
                //     System.out.println("You lost! The word was: " + secretWord);
                //     break; // Exit the loop
                // }
            }

            // Check if the word has been completely guessed
            if (!String.valueOf(guessedWord).contains("_")) {
                System.out.println("Congratulations! You guessed the word: " + secretWord);
                break; // Exit the loop
            }
        }

        scanner.close(); // Close the scanner when the game loop is done
    }
}
