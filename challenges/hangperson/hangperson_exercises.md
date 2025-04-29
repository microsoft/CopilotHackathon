# GitHub Copilot Exercises: Guided Implementation & Customization (Hangperson App)

**Session Goal:** Learn to guide and customize GitHub Copilot for new project development, focusing on planning, specification, defining guidelines (code style, quality, testing), creating reusable prompts, defining agent workflows, and iteratively refining instructions.

**Project Context:** We start with an empty repository containing only the `README.md` file for setting up the "HangpersonApp" Java console application.

**Key Customization Mechanisms:**

* **Workspace Instructions (`.github/copilot-instructions.md`):** General project context.
* **Custom Instructions (VS Code Settings / Files):** Specific guidelines for code generation, test generation, etc., configured via `settings.json` often by referencing external `.md` instruction files (e.g., from a `docs/instructions/` folder).
* **Reusable Prompts (`*.prompt.md`):** Parameterized instructions for common, limited-scope tasks.
* **Agent Workflow Instructions (Separate `.md` File):** Detailed, multi-step instructions for complex agent tasks.

**Prerequisites:**

* Familiarity with basic Copilot Chat features (`@workspace`, `#` references, `/explain`, `/tests`, Edits mode, Inline Chat) from previous sessions.
* JDK (1.8+) and Maven installed.
* VS Code with GitHub Copilot extensions enabled.
* Understanding of how to access VS Code settings (`settings.json`).

---

## Section 1: Project Setup & Initial Exploration

**Goal:** Set up the basic project structure using the provided `README.md` and Copilot prompts, reinforcing basic interaction and critical review of build configurations.

---

### Exercise 1.1: Initialize Project with Maven

* **Purpose:** To create the standard Maven project structure.
* **Aim:** Follow the `README.md` instructions to generate the project skeleton using the Maven archetype command in the terminal.
* **Steps:**
    1.  Open your system terminal (not necessarily VS Code's integrated terminal yet).
    2.  Navigate to the directory containing the `README.md` file.
    3.  Run the Maven command from the README:
        ```bash
        mvn archetype:generate -DgroupId=com.example -DartifactId=hangpersonapp -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
        ```
    4.  Navigate into the newly created `hangpersonapp` directory:
        ```bash
        cd hangpersonapp
        ```
    5.  Open this specific `hangpersonapp` folder in a new VS Code window:
        ```bash
        code .
        ```
    6.  *(Optional)* Open the `README.md` from the *parent* directory in this new VS Code window for reference if needed.

### Exercise 1.2: Configure `pom.xml` (Guided by User)

* **Purpose:** To configure the project's build settings (Java version, testing framework) by asking Copilot for specific configurations and critically reviewing the suggestions.
* **Aim:** Practice guiding Copilot to generate specific `pom.xml` snippets and understand the importance of verifying AI-suggested build configurations.
* **Steps:**
    1.  Open the `pom.xml` file in VS Code.
    2.  Open the Copilot Chat view.
    3.  **Guide Copilot Step-by-Step:** Instead of asking Copilot to make changes directly, ask for the necessary XML snippets and review them carefully before adding them manually or using Edits mode.
        * **Java Version:**
            * Prompt: `#file:pom.xml /explain Show me the XML needed within the <properties> section to set the Java compiler source and target versions to 1.8.`
            * Review the suggested `<maven.compiler.source>1.8</maven.compiler.source>` and `<maven.compiler.target>1.8</maven.compiler.target>` properties. Add them inside the existing `<properties>` tags.
        * **JUnit 5 Dependency:**
            * Prompt: `#file:pom.xml /explain What is the Maven dependency XML snippet needed to add the JUnit 5 Jupiter engine for testing? Use a recent version like 5.8.2 or later and set the scope to 'test'.`
            * Review the suggested `<dependency>` block. Verify the `groupId`, `artifactId`, `version`, and `scope`. Add it inside the `<dependencies>` section.
        * **Surefire Plugin:**
            * Prompt: `#file:pom.xml /explain Show me the XML configuration for the maven-surefire-plugin (version 3.0.0-M5 or later) needed within the <build><plugins> section to ensure it works correctly with JUnit 5.`
            * Review the suggested `<plugin>` configuration. Add it inside the `<plugins>` section (create `<build><plugins>` if they don't exist).
        * **(Optional) Assembly Plugin:**
            * Prompt: `#file:pom.xml /explain Show me the XML configuration for the maven-assembly-plugin (version 3.3.0 or later) to create an executable JAR with dependencies. How do I configure the main class as com.example.App?`
            * Review the suggested `<plugin>` configuration carefully, especially the `<mainClass>` element and `<descriptorRef>`. Add it inside the `<plugins>` section.
    4.  **Verification and Warning:**
        * Compare your final `pom.xml` structure against the example in the `README.md`.
        * **Critical Warning:** Build files like `pom.xml` define crucial aspects of your project (dependencies, versions, build process). While Copilot can provide correct snippets, it might suggest outdated versions, incompatible configurations, or unnecessary elements. **Always critically review AI suggestions for `pom.xml`. Verify dependency versions against official repositories (like Maven Central) and plugin configurations against their official documentation.** Do not blindly accept changes to your build configuration.
    5.  Save the `pom.xml` file.

### Exercise 1.3: Create Basic App and Test Stubs with Copilot

* **Purpose:** To generate the initial `App.java` and `AppTest.java` files using Copilot prompts based on the `README.md`.
* **Aim:** Use Copilot Chat to generate simple starting code for the main application and its test.
* **Steps:**
    1.  **App.java:**
        * Navigate to `src/main/java/com/example/App.java` (Copilot might offer to create it if it doesn't exist, or you can create it manually).
        * Open the Copilot Chat view.
        * Prompt: `#file:src/main/java/com/example/App.java /explain Create a basic public class App in package com.example with a main method that prints 'Hello HangpersonApp!' and a public method getGreeting() that returns the string 'Hello HangpersonApp!'` (Adjust path if needed). Apply the suggestion.
    2.  **AppTest.java:**
        * Navigate to `src/test/java/com/example/AppTest.java` (Create if needed).
        * Open the Copilot Chat view.
        * Prompt: `#file:src/test/java/com/example/AppTest.java /explain Create a JUnit 5 test class AppTest in package com.example. Add a test method named testGetGreeting that asserts the getGreeting method of the App class returns 'Hello HangpersonApp!'. Include necessary imports.` Apply the suggestion.
    3.  Verify the generated files match the examples in the `README.md`.
    4.  **Build & Test:** Open the integrated terminal (View > Terminal) and run `mvn compile` and `mvn test` to ensure the basic setup works.

---

## Section 2: Planning and Specification

**Goal:** Use Copilot to discuss and define the requirements and architecture for the Hangperson game *before* writing implementation code.

---

### Exercise 2.1: Discuss Game Components with Copilot

* **Purpose:** To brainstorm the necessary classes and logic for the Hangperson game.
* **Aim:** Use Copilot Chat to outline the core components needed.
* **Steps:**
    1.  Open the Copilot Chat view.
    2.  Prompt: `@workspace Based on the description of the Hangperson game in the README (guessing words, tracking incorrect guesses, drawing parts), what core Java classes or components would be needed? Think about separating concerns like game state/logic, word management, user interaction (input/output), and tracking guesses.`
    3.  Discuss Copilot's suggestions. Ask follow-up questions like "How would the GameEngine interact with the WordSource?" or "What information does the GameState need to track?".

### Exercise 2.2: Generate Architectural Diagram

* **Purpose:** To visualize the relationship between the discussed components.
* **Aim:** Use Copilot Chat to generate a Mermaid class diagram description based on the component discussion.
* **Steps:**
    1.  Continue the chat from Exercise 2.1 or start a new one referencing the previous discussion.
    2.  Prompt: `@workspace Based on our discussion of components like GameEngine, WordSource, GameState, ConsoleInput, ConsoleOutput, generate a Mermaid class diagram description showing these classes and their primary relationships (e.g., uses, contains).`
    3.  Copy the generated ```mermaid ... ``` block.
    4.  Create a new file `docs/Architecture.md` (create `docs` folder if needed).
    5.  Paste the Mermaid block into the file and save it. You can preview it in VS Code if you have a Mermaid extension installed.

### Exercise 2.3: Generate Game Specification

* **Purpose:** To create a more formal specification for the core game loop.
* **Aim:** Use Copilot Chat to generate a Markdown specification document.
* **Steps:**
    1.  Open the Copilot Chat view.
    2.  Prompt: `@workspace Generate a Markdown specification for the core gameplay loop of the Hangperson game. It should cover:
        - Starting a new game (selecting a random word from a predefined source - mention using nouns/verbs files later).
        - Displaying the initial game state (e.g., underscores for the word, incorrect guesses count).
        - Handling player letter guesses (validating input, checking if letter is in the word, handling already guessed letters).
        - Updating game state based on correct/incorrect guesses (revealing letters, incrementing incorrect guess count).
        - Displaying updated game state after each guess.
        - Determining win/loss conditions (word fully guessed vs. max incorrect guesses reached).
        - Ending the game and displaying the outcome.`
    3.  Review the generated specification for clarity and completeness.
    4.  Create a new file `docs/specs/GameSpec.md` (create `specs` folder if needed).
    5.  Paste the generated Markdown into the file and save it.

---

## Section 3: Defining Customization & Guidelines

**Goal:** Configure Copilot's behavior using workspace instructions and custom settings before generating significant code.

---

### Exercise 3.1: Define Workspace Instructions (`.github/copilot-instructions.md`)

* **Purpose:** To provide Copilot with persistent, high-level project context.
* **Aim:** Create/update the `.github/copilot-instructions.md` file.
* **Steps:**
    1.  Ensure the `.github` folder exists at the project root.
    2.  Create or open `.github/copilot-instructions.md`.
    3.  Add content relevant to this *new* project:
        ```markdown
        # Copilot Instructions for HangpersonApp

        ## Project Overview
        This is a command-line Hangperson (word guessing) game written in Java 1.8 and built using Apache Maven.

        ## Core Technologies
        - Java 1.8
        - Maven
        - JUnit 5

        ## Key Architectural Ideas (Initial)
        - Separate game logic (GameEngine) from user I/O (ConsoleInput/Output).
        - Manage game state (word to guess, guessed letters, incorrect attempts).
        - Use word lists (nouns/verbs provided).

        ## General Coding Guidelines
        - Prefer clear, descriptive variable and method names.
        - Add Javadoc comments for public methods.
        - Use try-with-resources for file I/O when reading word lists.
        - Prefer SLF4j for any necessary logging (though System.out is acceptable for direct game output).
        ```
    4.  Save the file.
    5.  **Verification (Optional):** Ask Copilot Chat `@workspace What Java version is this project using?` and see if it correctly answers "1.8".

### Exercise 3.2: Define Custom Code Generation Instructions (via File Reference)

* **Purpose:** To guide Copilot towards generating code that meets specific quality standards, especially for error handling and style, using a referenced instruction file.
* **Aim:** Create a `.md` instruction file for Java code generation guidelines in `docs/instructions/` and reference it in workspace `settings.json`.
* **Steps:**
    1.  **Create Instruction File:**
        * Create a folder `docs/instructions/` in your workspace root (create `docs` and `instructions` folders if they don't exist).
        * Inside `docs/instructions/`, create a file named `copilot_codegen_java_instructions.md`.
        * Add the following content to this new file:
            ```markdown
            ## Java Code Generation Guidelines (HangpersonApp)

            - **Error Handling:**
              - For user input (e.g., reading guesses), handle potential exceptions gracefully (e.g., invalid format). Inform the user and prompt again.
              - For file I/O (reading word lists), use try-with-resources and catch specific IOExceptions. Propagate errors appropriately (e.g., throw a custom exception or return an empty list). Log errors using SLF4j if available, otherwise System.err.
            - **Style:**
              - Keep methods relatively short and focused on a single responsibility.
              - Add comments explaining non-obvious logic.
              - Adhere to Java 1.8 syntax compatibility.
            ```
        * Save `docs/instructions/copilot_codegen_java_instructions.md`.
    2.  **Open Workspace Settings (JSON):** Use the Command Palette (`Cmd+Shift+P` / `Ctrl+Shift+P`) and search for `Preferences: Open Workspace Settings (JSON)`.
    3.  **Reference Instruction File:** Add or modify the relevant setting for code generation instructions (e.g., `github.copilot.chat.codeGeneration.instructions` - check documentation for the exact key name). Reference the file you created:
        ```json
        {
          // ... other settings ...
          "github.copilot.chat.codeGeneration.instructions": [
            {
              // Use relative path from workspace root
              "file": "docs/instructions/copilot_codegen_java_instructions.md"
              // You could add more { "text": "..." } or { "file": "..." } entries here
            }
          ]
          // ... other settings ...
        }
        ```
        *Note: Ensure the setting key used corresponds to the Copilot feature you want to influence (e.g., chat suggestions, inline completions). Refer to current Copilot documentation.*
    4.  Save `settings.json`.
    5.  **Verification:** Perform the verification steps from the previous version of this exercise (asking Copilot to generate code involving potential I/O) and check if the output adheres to the rules defined in your `docs/instructions/copilot_codegen_java_instructions.md` file.

### Exercise 3.3: Define Custom Test Generation Instructions (via File Reference)

* **Purpose:** To ensure generated unit tests follow consistent standards using a referenced instruction file.
* **Aim:** Create a `.md` instruction file for Java test generation guidelines in `docs/instructions/` and reference it in workspace `settings.json`.
* **Steps:**
    1.  **Create Instruction File:**
        * Inside the `docs/instructions/` folder, create a file named `copilot_testgen_java_instructions.md`.
        * Add the following content:
            ```markdown
            ## Java Test Generation Guidelines (HangpersonApp)

            - **Framework:** Use JUnit 5 (`org.junit.jupiter.api`).
            - **Assertions:** Use standard JUnit 5 assertions (`org.junit.jupiter.api.Assertions.*`).
            - **Naming:** Use descriptive names, e.g., `testMethodName_WithSpecificInput_ShouldReturnExpected`.
            - **Setup/Teardown:** Use `@BeforeEach` / `@AfterEach` where appropriate.
            - **Mocking:** Use Mockito if dependencies need mocking (though initial tests might not require it).
            - **Structure:** Include clear Arrange, Act, Assert sections commented as `// Arrange`, `// Act`, `// Assert`.
            ```
        * Save `docs/instructions/copilot_testgen_java_instructions.md`.
    2.  **Open Workspace Settings (JSON).**
    3.  **Reference Instruction File:** Add or modify the relevant setting for test generation instructions (e.g., `github.copilot.tests.instructions` - check documentation for the exact key).
        ```json
        {
          // ... other settings ...
          "github.copilot.tests.instructions": [ // Hypothetical key, check actual setting name
             {
               "file": "docs/instructions/copilot_testgen_java_instructions.md"
             }
          ]
          // ... other settings ...
        }
        ```
    4.  Save `settings.json`.
    5.  **Verification:** Perform the verification steps from the previous version of this exercise (using `/tests` command) and check if the generated tests adhere to the rules defined in your `docs/instructions/copilot_testgen_java_instructions.md` file.

---

## Section 4: Guided Implementation & Customization Refinement

**Goal:** Implement parts of the game using Copilot, guided by the specifications and custom instructions, while also creating reusable prompts and agent workflows.

---

### Exercise 4.1: Create Reusable Prompt (Word Selection)

* **Purpose:** To create a reusable way to generate code for selecting a random word.
* **Aim:** Create a `.prompt.md` file for generating a Java method to pick a random word from a hardcoded list (as a starting point).
* **Steps:**
    1.  Create a `.prompts` folder in the workspace root if it doesn't exist.
    2.  Create `.prompts/select_random_word.prompt.md` with the following content:
        ```markdown
        ---
        name: Select Random Word (Simple)
        description: Generates a Java method to select a random word from a predefined list.
        variables:
          - name: methodName
            description: The desired name for the method (e.g., selectRandomWord).
            required: true
          - name: className
            description: The name of the class this method should belong to.
            required: true
        ---

        Generate a private static Java method named `{{methodName}}` within a class named `{{className}}`.
        - This method should take no arguments.
        - It should define a small, hardcoded list of sample words (e.g., "java", "copilot", "maven", "game").
        - It should randomly select and return one word from this list.
        - Include necessary imports (e.g., `java.util.List`, `java.util.Random`, `java.util.Arrays`).
        - Provide only the method code.
        ```
    3.  Save the file.
    4.  **Verification:**
        * In Copilot Chat, invoke the prompt: `# (select .prompts/select_random_word.prompt.md)`
        * Copilot will likely ask for `methodName` and `className`. Provide `selectRandomWord` and `WordSource` respectively.
        * Examine the generated method code. Does it correctly select a random word from a list?

### Exercise 4.2: Define Agent Workflow (Core Game Logic)

* **Purpose:** To define instructions for an agent to implement the core game structure based on the specification.
* **Aim:** Create `implement_game_core_workflow.md` guiding the agent.
* **Steps:**
    1.  Create `implement_game_core_workflow.md` in the workspace root or an `/instructions` folder (e.g., `docs/instructions/implement_game_core_workflow.md`).
    2.  Add the following content:
        ```markdown
        # AI Agent Workflow: Implement Hangperson Core Logic

        ## System Prompt
        You are a Java developer implementing the core logic for a Hangperson game based on a specification. Adhere strictly to project standards (Java 1.8, JUnit 5, custom instructions for error handling/logging defined in referenced files, context from `.github/copilot-instructions.md`). Focus on creating the structure and basic logic flow.

        ## Workflow
        1.  **Analyze Spec:** Read the provided game specification (`#specFile`).
        2.  **Create Classes:** Based on the spec and architectural ideas (GameEngine, GameState, WordSource), create new Java files for these classes within the `com.example` package (or a suitable subpackage like `com.example.game`). Define basic class structures.
        3.  **Implement Word Source (Initial):** In the `WordSource` class, implement a method to get a word. For now, use the hardcoded list approach (potentially reusing logic from the reusable prompt exercise, or generating anew). Later, this can be modified to read from files.
        4.  **Implement Game State:** Define fields in `GameState` to track: the secret word, the set of correctly guessed letters, the set of incorrectly guessed letters, and the maximum allowed incorrect guesses (e.g., 6). Implement a constructor and methods to update state (e.g., `addCorrectGuess`, `addIncorrectGuess`) and check for win/loss conditions (`isWon`, `isLost`).
        5.  **Implement Game Engine (Core Loop):** In `GameEngine`, create a primary method (e.g., `playGame`). This method should:
            * Initialize a new game (get word from `WordSource`, create `GameState`).
            * Implement the main loop:
                * Display current game state (underscores, incorrect guesses).
                * Prompt player for a letter guess (handle basic input for now).
                * Process the guess (check if valid, update `GameState`).
                * Check for win/loss condition.
            * Handle basic user input errors according to custom instructions.
        6.  **Adhere to Standards:** Ensure generated code follows Java 1.8 syntax, uses specified logging/error handling patterns from referenced instruction files, and includes basic Javadoc/comments.
        7.  **Report:** List files created/modified and note areas needing further implementation (e.g., actual user input loop in `App.java`, file reading for words, drawing the hangman).

        **DO NOT implement the full user interface loop in `App.java` yet. Focus on the core classes and logic.**

        ## Input Variables
        - `#specFile`: Reference to the game specification file (e.g., `#docs/specs/GameSpec.md`).
        - `#codebase`: Access to the workspace code.
        ```
    3.  Save the file.

### Exercise 4.3: Execute Agent Workflow & Refine

* **Purpose:** To generate the core game logic using the defined workflow and then refine it.
* **Aim:** Invoke the agent workflow, review the generated code against standards, and use Copilot (Edits/Ask/Inline) to refactor or improve parts.
* **Steps:**
    1.  Ensure `docs/specs/GameSpec.md` exists.
    2.  In Copilot Chat, invoke the workflow:
        ```
        # (select docs/instructions/implement_game_core_workflow.md) /implement # (select docs/specs/GameSpec.md)
        ```
        *(Adjust invocation based on actual Copilot Agent capabilities and the location you saved the workflow file)*
    3.  **Review Generated Code:** Examine the created files (`GameEngine.java`, `GameState.java`, `WordSource.java`).
        * Does the structure match the plan?
        * Does the error handling (if any generated yet) match the custom instructions defined in your `docs/instructions/*.md` files?
        * Is the logic generally correct based on the spec?
    4.  **Refine (Example):**
        * Maybe the initial guess processing logic in `GameEngine` is too complex. Select that method.
        * Use Edits mode: `Refactor this method to improve clarity. Extract the logic for checking if a guess is valid into a separate private helper method.`
        * Use Inline Chat (`Cmd+I` / `Ctrl+I`) on a specific line: `/explain Explain this line of code.` or select a block and `/fix Fix potential issues in this code block according to project guidelines.` Review suggestions (`Alt+]`).
    5.  **Iterate:** Repeat review and refinement as needed. Did Copilot miss applying a custom instruction? Try prompting it again with the instruction explicitly mentioned.

---

## Section 5: Testing with Guidelines

**Goal:** Generate unit tests for the implemented game logic, ensuring they follow the defined testing standards.

---

### Exercise 5.1: Generate Tests for Game Logic

* **Purpose:** To create unit tests for the core game logic classes.
* **Aim:** Use the `/tests` command with the active custom test generation instructions (referenced via `settings.json`) to test `GameState` and potentially parts of `GameEngine`.
* **Steps:**
    1.  Open `GameState.java` (or the file created by the agent in Exercise 4.3).
    2.  In Copilot Chat, prompt: `# (select GameState.java) /tests Generate JUnit 5 tests for the public methods of this class (like constructor, addCorrectGuess, addIncorrectGuess, isWon, isLost). Follow the project's test guidelines.`
    3.  Copilot should create/update `GameStateTest.java`. Review the generated tests:
        * Are they JUnit 5?
        * Do they use the specified assertion style (standard JUnit 5)?
        * Do names follow the convention?
        * Is the Arrange/Act/Assert structure clear?
    4.  *(Optional)* Repeat for testable methods in `GameEngine.java`, mocking dependencies like `WordSource` if necessary: `# (select GameEngine.java) /tests Generate JUnit 5 tests for the processGuess method, mocking dependencies. Follow project test guidelines.`
    5.  Run `mvn test` to see if the generated tests pass (they might need minor adjustments).

---

## Section 6: (Optional) Advanced Customization & Next Steps

**Goal:** Explore further customization or tackle more complex implementation parts.

---

### Exercise 6.1: Refine Instructions or Prompts

* **Purpose:** To improve the guidance given to Copilot based on observed results.
* **Aim:** Modify the custom instructions (`docs/instructions/*.md` files referenced in `settings.json` or `.github/copilot-instructions.md`) or reusable prompts (`*.prompt.md`) to address any shortcomings noticed during implementation or testing.
* **Steps:**
    1.  Identify an area where Copilot's output didn't fully meet expectations (e.g., error handling wasn't robust enough, test naming was inconsistent despite instructions).
    2.  Refine the relevant instruction file (`.md`) or prompt file (`.prompt.md`) to be more specific or clear.
    3.  Re-run a relevant generation task (e.g., ask Copilot to regenerate a specific method or test) and see if the output improves.

### Exercise 6.2: Implement File-Based Word Source

* **Purpose:** To replace the hardcoded word list with logic to read from the provided `nouns`/`verbs` files.
* **Aim:** Use Copilot (Ask, Edits, Completion), guided by custom instructions for I/O error handling, to implement file reading in `WordSource.java`.
* **Steps:**
    1.  *(Setup)* Ensure you have `nouns.txt` and `verbs.txt` files in your project root or a `src/main/resources` directory.
    2.  Open `WordSource.java` (or the relevant file created by the agent).
    3.  Use Copilot Chat or Edits mode: `# (select WordSource.java) /explain Modify this class to read words from text files named 'nouns.txt' and 'verbs.txt' located in the project root (or classpath resources if placed there). Load all words into a single list upon initialization. Handle potential FileNotFoundException and IOException according to the project's error handling guidelines (use try-with-resources, log errors). Update the word selection method to use this list.`
    4.  Review and refine the generated code, ensuring robust file handling. Consider where the files should ideally be placed (resources folder is common for classpath loading).

### Exercise 6.3: Connect UI Loop

* **Purpose:** To integrate the core game logic with the main application loop for user interaction.
* **Aim:** Modify `App.java` to create instances of the game classes and run the main game loop, handling user input and displaying output.
* **Steps:**
    1.  Open `App.java`.
    2.  Use Copilot Chat/Edits/Completion: `# (select App.java) # (select GameEngine.java) /explain Modify the main method to: create a GameEngine instance, call its playGame method, and handle the overall application flow.` (You'll likely need more detailed prompts to handle reading user input from the console using `java.util.Scanner` and displaying game state).
    3.  Focus on getting the basic loop working, relying on the methods already implemented in `GameEngine`, `GameState`, etc.

---
