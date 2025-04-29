# Java Console Application Setup (HangpersonApp) with Copilot Assistance

This document describes how to set up a new testable Java console application using Maven, leveraging GitHub Copilot for assistance.

## Prerequisites

*   Java Development Kit (JDK) installed (version 8 or later recommended).
*   Apache Maven installed.
*   GitHub Copilot extension enabled in your IDE.

## Copilot Cheatsheet
A comprehensive [cheatsheet](./hangperson_cheatsheet.md) is available in this project, summarizing key ways to interact with GitHub Copilot from the previous learning sessions. It includes:
- Different interaction interfaces (Chat View, Inline Chat)
- How to provide context using `@workspace` and `#file` references
- Available commands and modes
- Best practices for code generation tasks
- Tips for guiding Copilot's behavior

Review this cheatsheet before starting either development approach - it will help you:
- Refresh your memory on Copilot's capabilities
- Guide you through the exercises more effectively
- Remind you of useful commands and contexts
- Help you get better results from Copilot's suggestions

## Setup Steps

1.  **Create Project Structure:**
    Open your terminal or command prompt, navigate to the directory where you want to create the project (the directory containing this `README.md` file), and run the following Maven command to generate a basic project structure:

    ```bash
    mvn archetype:generate -DgroupId=com.example -DartifactId=hangpersonapp -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
    ```

    This will create a `hangpersonapp` subdirectory with the standard Maven layout (`src/main/java`, `src/test/java`, `pom.xml`).

2.  **Navigate into the Project:**
    ```bash
    cd hangpersonapp
    ```

3.  **Create a new vscode window to contain only this app**
    ```bash
    code .
    ```
    Start using copilot in that new window (you can open this md file on that window also if needed)

## Development Approaches

You have two options for developing this application with GitHub Copilot:

### 1. Structured Learning Path (Recommended for Learning)
Follow the detailed [exercises here](./hangperson_exercises.md). This approach:
- Focuses on creating custom instructions and reusable prompts
- Teaches you how to effectively customize Copilot's behavior
- Provides step-by-step guidance for:
  - Setting up project infrastructure
  - Creating custom instructions
  - Defining reusable prompts
  - Implementing core game logic
  - Writing tests
- Best for learning Copilot's advanced features and best practices

### 2. Quick Start Path
Use the [ready-made prompts here](./hangperson_prompts.md). This approach
- Provides immediate prompts to bootstrap your project
- Gets you coding faster with less setup
- Includes working example code snippets
- Best for rapid prototyping or if you're already familiar with Copilot

Choose the approach that best fits your learning goals and time constraints. The structured path will teach you more about Copilot's capabilities, while the quick start path will get you coding faster.

## Building and Running

1.  **Compile:**
    ```bash
    mvn compile
    ```

2.  **Run Tests:**
    ```bash
    mvn test
    ```

3.  **Package (Create JAR):**
    ```bash
    mvn package
    ```
    This will create JAR files in the `target/` directory. If you included the `maven-assembly-plugin`, it will create an executable JAR with dependencies.

4.  **Run the Application (using the executable JAR):**
    ```bash
    java -jar target/hangpersonapp-1.0-SNAPSHOT-jar-with-dependencies.jar
    ```
    *(Adjust the JAR filename based on your project's version)*

    Alternatively, run directly using Maven:
    ```bash
    mvn exec:java -Dexec.mainClass="com.example.App"
    ```
    *(Make sure to replace `com.example.App` with your actual main class if different)*

## Actual game implementation

1. Start implementing a console game where you guess a random word.

    Hangperson (traditionally known as Hangman) is a classic word-guessing game. One player thinks of a word or phrase, and the other player tries to figure it out by guessing letters one at a time. For each incorrect guess, a part of a stick figure being hanged is drawn. The goal is to guess the word before the drawing is completed.

    You can use that description for Copilot, but try to ask only for small parts of the implementation at once.

    You can, e.g., first use ask mode to plan what parts you could ask agent mode to implement at once and how to limit agent mode not to try to do the whole game in one go.

    There are nouns and verbs available in this directory for you to use as words or in any way you would like. Please see `wordnet.md` for the license for nouns and verbs.

2. When the game is ready or in good shape, consider adding tests if none exist yet.

    Before adding tests, it would be good to refactor the game to be modular enough. Try to extract the game engine, input parsing, and rendering. If you can come up with better modules with Copilot, try to use those.

## Challenging features

1. Make a game server and a client.

2. Make bots (AI opponents).

3. Implement chat between clients
