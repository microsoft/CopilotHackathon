1.  **ask**

    @workspace Hangperson (traditionally known as Hangman) is a classic word-guessing game. One player thinks of a word or phrase, and the other player tries to figure it out by guessing letters one at a time. For each incorrect guess, a part of a stick figure being hanged is drawn. The goal is to guess the word before the drawing is completed.

    i would like to implement this game with copilot

    what could be my first prompt for agent so that i get only first parts done and not everything at once. i expect that everyting at once would be too compilicated app and not simple enouhg

    **response**

    Okay, to start building the Hangperson game incrementally, you could ask for the initial setup. Here's a possible first prompt:

    "Modify the main method in App.java to:

    Define a secret word (e.g., "hangman").
    Initialize a character array or list to represent the guessed word, initially filled with underscores (_) for each letter in the secret word.
    Print the initial state of the guessed word (e.g., _ _ _ _ _ _ _).
    Prompt the user to enter their first letter guess.
    Read the user's input."
    This focuses on setting up the core variables and handling the very first user interaction without implementing the full game logic yet.

2.  **ask**

    game now stops after i guess any character

    i think next step could be that game keeps asking characters until whole word is filled and then game stops

    please give me good prompt for agent to do this

    **response**

    Okay, here is a prompt you can use:

    "Modify the Hangman game logic. Currently, the game ends after the user makes a single guess. Change the game loop so that it repeatedly prompts the user for a character guess until the entire secret word has been guessed correctly. Ensure the game state (like the displayed word with guessed letters) is updated after each valid guess."


3. junit 5

    From the challenge directory use promts as described in readme to add junit5 compatibility and convert `AppTest.java` to junit5 format.