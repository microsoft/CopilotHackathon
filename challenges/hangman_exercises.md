# GitHub Copilot Exercises: Customizing and Guiding Copilot

**Session Goal:** Learn how to customize GitHub Copilot's behavior using workspace instructions, feature-specific settings, reusable prompts, and agent workflow definitions to align its output with project standards, improve code quality, and automate complex tasks.

**Project Context:** We will continue using the Simple Weather CLI Java application from the previous session. Assume the basic project structure and code are available.

**Key Customization Mechanisms:**

* **Workspace Instructions (`.github/copilot-instructions.md`):** Provides general project context and high-level guidelines to Copilot for all interactions within the workspace.
* **Custom Instructions (VS Code Settings):** Allows defining specific instructions for different Copilot features (code generation, test generation, code review, etc.) via `settings.json` or by referencing external `.md` files. These can be global or workspace-specific.
* **Reusable Prompts (`*.prompt.md`):** Markdown files containing specific, parameterized instructions for repeatable, limited-scope tasks, referenced directly in chat prompts.
* **Agent Workflow Instructions (Separate `.md` File):** Detailed, multi-step instructions, potentially including commands and logic, designed to guide Copilot Agents (like `/new` or hypothetical future agents) through complex workflows.

**Prerequisites:**

* Completion of the previous session's exercises (familiarity with basic Copilot Chat features, variables like `#`, participants like `@workspace`).
* The Simple Weather CLI project opened in VS Code.
* Understanding of how to access VS Code settings (`settings.json`).

---

## Section 1: Workspace-Level Guidance (`.github/copilot-instructions.md`)

**Goal:** Provide Copilot with persistent, high-level context about the project.

---

### Exercise 1.1: Define Project Context

* **Purpose:** To inform Copilot about the project's overall purpose, technology stack, and key architectural decisions so it can provide more relevant assistance.
* **Aim:** Create a `.github/copilot-instructions.md` file and populate it with essential project context. Verify Copilot acknowledges this context.
* **Steps:**
    1.  In the root of your project workspace, create a folder named `.github` if it doesn't exist.
    2.  Inside the `.github` folder, create a new file named `copilot-instructions.md`.
    3.  Add the following content (or adapt it based on the actual project state):
        ```markdown
        # Copilot Instructions for Simple Weather CLI App

        ## Project Overview
        This is a command-line application written in Java 11+ and built using Apache Maven.
        Its purpose is to fetch current weather data for a specified city from the OpenWeatherMap API.

        ## Core Technologies
        - Java (11+)
        - Maven (for build and dependency management)
        - JUnit 5 (for testing)
        - Mockito (for mocking dependencies in tests)
        - Jackson or org.json (for JSON parsing - check pom.xml)
        - SLF4j with a simple backend (e.g., slf4j-simple) should be preferred for logging.

        ## Key Architectural Points
        - Uses OpenWeatherMap API (requires API key via `OPENWEATHERMAP_API_KEY` env var).
        - `WeatherApiClient` interface defines the contract for API interaction.
        - `OpenWeatherMapClient` is the current implementation.
        - `WeatherService` orchestrates fetching data.
        - `WeatherData` is the primary data model.
        - Configuration is handled via `ConfigUtil`.
        - Exceptions should generally be wrapped in `WeatherApiException`.
        ```
    4.  Save the file.
    5.  **Verification:**
        * Open the Copilot Chat view.
        * Ensure Copilot is configured to use workspace instructions (this is often automatic, but check Copilot settings if needed).
        * Ask a question that requires context: `@workspace /explain What logging framework should be preferred in this project?`
        * Observe if Copilot's answer reflects the information ("SLF4j") provided in the instructions file.

---

## Section 2: Feature-Specific Custom Instructions (Settings / Referenced Files)

**Goal:** Tailor Copilot's output for specific features like code and test generation to meet project standards.

---

### Exercise 2.1: Customizing Code Generation (Error Handling)

* **Purpose:** To guide Copilot to generate Java code that follows specific error handling practices, addressing common issues like inadequate try-catch blocks.
* **Aim:** Define custom instructions for code generation in VS Code settings (workspace `settings.json`) to enforce specific try-catch patterns and logging within catch blocks. Generate code and verify.
* **Steps:**
    1.  **Open Workspace Settings:** Use the Command Palette (`Cmd+Shift+P` / `Ctrl+Shift+P`) and search for `Preferences: Open Workspace Settings (JSON)`.
    2.  **Add Custom Instruction:** Add or modify the `github.copilot.editor.instructions` section (or a similar setting specific to code generation if available - check documentation for exact key names as they might evolve). Define instructions for Java:
        ```json
        {
          // ... other settings ...
          "github.copilot.editor.instructions": {
            "language": {
              "java": {
                "content": [
                  "## Java Code Generation Guidelines",
                  "- **Error Handling:** When generating code that involves I/O, network calls, or other potentially failing operations:",
                  "  - Use try-with-resources for managing resources like streams or connections.",
                  "  - Catch specific, relevant exceptions rather than generic `Exception`.",
                  "  - Inside catch blocks, log the exception using SLF4j `Logger.error(\"Error message\", exception);`. Do not just print the stack trace to System.err.",
                  "  - If appropriate, re-throw exceptions wrapped in the project's custom `WeatherApiException`."
                ]
                // Alternatively, reference a file:
                // "uri": "file:///${workspaceFolder}/.vscode/copilot_codegen_instructions.md"
              }
            }
            // ... other languages ...
          }
          // ... other settings ...
        }
        ```
        *Note: The exact setting key (`github.copilot.editor.instructions`) might change. Refer to the latest Copilot Chat extension settings.*
    3.  Save `settings.json`.
    4.  **Verification:**
        * Go to `OpenWeatherMapClient.java`.
        * Select the code block inside the `fetchWeatherData` method that makes the HTTP request and parses the response.
        * Use inline chat (`Cmd+I` / `Ctrl+I`) or the Chat view (Edits mode) and ask Copilot to rewrite the selected block, explicitly adding file reading logic *before* the network call (this is just to force potential I/O exceptions): `Rewrite this block. Before making the network call, attempt to read a configuration detail from a dummy file '/tmp/dummy_weather_config.txt'. Handle potential IOExceptions according to the project's error handling guidelines.`
        * Examine the generated code. Does it use try-with-resources for the file reading? Does it catch `IOException` specifically? Does the catch block use an SLF4j logger (you might need to add the logger field manually or ask Copilot to)?

### Exercise 2.2: Customizing Test Generation

* **Purpose:** To ensure generated unit tests adhere to the project's testing standards.
* **Aim:** Define custom instructions specifically for test generation, specifying framework, assertion style, and naming conventions. Generate tests and verify.
* **Steps:**
    1.  **Open Workspace Settings (JSON):** (As in Exercise 2.1).
    2.  **Add Test Instruction:** Add or modify the relevant setting for test generation instructions (e.g., `github.copilot.tests.instructions` - check documentation for the exact key).
        ```json
        {
          // ... other settings ...
          "github.copilot.tests.instructions": { // Hypothetical setting key - check actual
            "language": {
              "java": {
                "content": [
                  "## Java Test Generation Guidelines",
                  "- **Framework:** Use JUnit 5 (`org.junit.jupiter.api`).",
                  "- **Assertions:** Prefer AssertJ assertions (`org.assertj.core.api.Assertions.assertThat`).",
                  "- **Naming:** Follow the convention `methodName_whenCondition_thenExpectedResult`.",
                  "- **Setup/Teardown:** Use `@BeforeEach` for setup and `@AfterEach` for teardown.",
                  "- **Mocking:** Use Mockito (`org.mockito.Mockito`) for mocking dependencies.",
                  "- **Structure:** Include clear Arrange, Act, Assert sections commented as `// Arrange`, `// Act`, `// Assert`."
                ]
                // Alternatively, reference a file:
                // "uri": "file:///${workspaceFolder}/.vscode/copilot_testgen_instructions.md"
              }
            }
          }
          // ... other settings ...
        }
        ```
    3.  Save `settings.json`.
    4.  **Verification:**
        * Go to `WeatherService.java`.
        * Select the `getWeather` method signature.
        * Open the Copilot Chat view.
        * Use the `/tests` command: `#selection /tests Generate unit tests for the selected method, ensuring dependencies are mocked.`
        * Examine the generated test(s) in `WeatherServiceTest.java`. Do they use JUnit 5 annotations? Do they use AssertJ? Do the names follow the convention? Is the Arrange/Act/Assert structure present?

---

## Section 3: Reusable Prompts (`.prompt.md`)

**Goal:** Create reusable, parameterized prompts for common, limited-scope tasks.

---

### Exercise 3.1: Reusable Prompt for Sequence Diagram Generation

* **Purpose:** To create a standard way to ask Copilot to generate Mermaid sequence diagrams from code.
* **Aim:** Create a `.prompt.md` file that takes selected code and generates a sequence diagram description.
* **Steps:**
    1.  Create a folder named `.prompts` (or any name you prefer) in your workspace root.
    2.  Inside `.prompts`, create a file named `generate_sequence_diagram.prompt.md`.
    3.  Add the following content:
        ```markdown
        ---
        name: Generate Sequence Diagram
        description: Creates a Mermaid sequence diagram description based on the selected Java code.
        variables:
          - name: selectedCode
            description: The Java code selection to diagram.
            required: true
        ---

        Analyze the following Java code selection:

        ```java
        {{selectedCode}}
        ```

        Generate a Mermaid sequence diagram description representing the interactions within this code.
        - Identify the main actors/classes involved.
        - Show the sequence of method calls between them.
        - Use clear message descriptions based on method names.
        - Format the output enclosed in a Mermaid code block (```mermaid ... ```).
        ```
    4.  Save the file.
    5.  **Verification:**
        * Go to `WeatherService.java` and select the body of the `getWeather` method.
        * Open the Copilot Chat view.
        * Reference the reusable prompt (the exact syntax might vary, could be via a specific UI element or a chat command like `/prompt` or by referencing the file path): `# (select .prompts/generate_sequence_diagram.prompt.md)` (or use the UI if available). Copilot might then prompt for the `selectedCode` variable, which should automatically pick up your editor selection.
        * Alternatively, try: `#selection # (.prompts/generate_sequence_diagram.prompt.md)`
        * Examine the output. Does it contain a valid Mermaid sequence diagram block describing the interactions (e.g., `WeatherService -> WeatherApiClient: fetchWeatherData`)?

### Exercise 3.2: Reusable Prompt for Refactoring to Optional

* **Purpose:** To create a quick way to refactor methods that might return null.
* **Aim:** Create a `.prompt.md` file for refactoring a selected Java method to return `java.util.Optional`.
* **Steps:**
    1.  Inside the `.prompts` folder, create `refactor_to_optional.prompt.md`.
    2.  Add the following content:
        ```markdown
        ---
        name: Refactor to Optional
        description: Refactors the selected Java method to return java.util.Optional instead of potentially returning null.
        variables:
          - name: selectedMethod
            description: The Java method selection to refactor.
            required: true
        ---

        Refactor the following Java method to return `java.util.Optional<ReturnType>` instead of `ReturnType`, where `ReturnType` is the original return type.
        - If the method originally returned a non-null value `x`, it should now return `Optional.of(x)`.
        - If the method originally returned `null`, it should now return `Optional.empty()`.
        - Update the method signature accordingly.
        - Provide only the refactored method code.

        ```java
        {{selectedMethod}}
        ```
    3.  Save the file.
    4.  **Verification:**
        * *(Self-Correction: The current codebase might not have a good candidate method returning null. Let's temporarily modify one for the exercise)*. Go to `ConfigUtil.java` and temporarily modify `getApiKey()` to return `null` if the environment variable is not set (instead of throwing an exception).
        * Select the modified `getApiKey` method.
        * In Copilot Chat, reference the reusable prompt: `#selection # (.prompts/refactor_to_optional.prompt.md)` (or use UI/command).
        * Examine the suggested refactored code. Does the return type change to `Optional<String>`? Does it return `Optional.of(apiKey)` or `Optional.empty()` appropriately?
        * *(Remember to revert the change to `ConfigUtil.java` afterwards!)*

---

## Section 4: Agent Workflow Definition (Separate Instruction File)

**Goal:** Define a complex, multi-step workflow for a Copilot Agent to follow, guided by a dedicated instruction file.

---

### Exercise 4.1: Define Feature Implementation Workflow

* **Purpose:** To create a detailed instruction set guiding an agent to implement a feature based on a specification, adhering to project standards.
* **Aim:** Create an `implement_feature_workflow.md` file outlining the steps, checks, and output for implementing the "Units" feature specified earlier.
* **Steps:**
    1.  Create a file named `implement_feature_workflow.md` in the root of your workspace (or a dedicated `/instructions` folder).
    2.  Add the following content, structuring it similarly to the security audit example:
        ```markdown
        # AI Agent Workflow: Implement Feature from Specification

        ## System Prompt for the AI Agent

        You are a meticulous Java developer tasked with implementing a new feature based on a provided specification file. You must adhere strictly to the project's coding standards, including those defined in `.github/copilot-instructions.md` and any relevant custom instructions (like logging and error handling). Your goal is to create or modify the necessary files, add the required logic placeholders, and ensure the code structure aligns with the project's architecture.

        ## Workflow

        1.  **Read Specification:** Analyze the provided feature specification file (`#specFile` variable). Identify the core requirements, affected components, and expected changes.
        2.  **Identify Target Files:** Based on the specification and existing codebase (`#codebase`), determine which Java files require modification or creation (e.g., `WeatherApp.java`, `WeatherData.java`, potentially new classes).
        3.  **Implement Argument Parsing:** Modify `WeatherApp.java`'s `main` method (or relevant argument handling logic) to parse the new command-line arguments specified in the spec (e.g., `--units`). Use basic `args` loop checking or integrate with a CLI library if one is already used. Handle potential parsing errors gracefully.
        4.  **Update Data Model:** Modify `WeatherData.java` if the specification requires storing additional state (e.g., the requested unit). Add necessary fields, constructor parameters, and getters/setters.
        5.  **Modify Service/Client (If Needed):** If the feature requires changes to how data is fetched or processed (e.g., requesting specific units from the API, performing conversions), modify `WeatherService.java` or `OpenWeatherMapClient.java` accordingly. Add placeholders for complex logic.
        6.  **Update Output Display:** Modify the output logic (likely in `WeatherApp.java`) to incorporate the new feature, such as displaying temperature in the selected unit according to the spec.
        7.  **Adhere to Standards:** Throughout implementation, strictly follow guidelines from `.github/copilot-instructions.md` and configured custom instructions, especially regarding logging (SLF4j) and error handling (try-with-resources, specific exceptions, logging errors).
        8.  **Generate Report:** Create a summary report listing:
            * Files created.
            * Files modified with a brief description of changes made to each.
            * Any assumptions made or areas requiring further manual implementation (e.g., complex conversion logic).

        **DO NOT add complex business logic beyond what's needed for structure and argument handling. Use placeholders like `// TODO: Implement temperature conversion logic here`.**

        ## Input Variables
        - `#specFile`: Reference to the Markdown specification file (e.g., `#docs/specs/UnitsFeature.md`).
        - `#codebase`: Access to the entire workspace code for context.

        ```
    4.  Save the `implement_feature_workflow.md` file.

### Exercise 4.2: Execute the Agent Workflow

* **Purpose:** To invoke the defined workflow using Copilot Chat, providing the necessary inputs.
* **Aim:** Use the Chat view to reference the workflow instruction file and the feature specification file to trigger the agent.
* **Steps:**
    1.  Ensure the `UnitsFeature.md` specification file exists from the previous session's Exercise 3.7 (or quickly regenerate it using Ask mode).
    2.  Open the Copilot Chat view.
    3.  **Invoke the Agent:** Type a prompt referencing the workflow instructions and the specification. The exact syntax for invoking an agent with custom instructions might vary, but try something like this:
        ```
        # (select implement_feature_workflow.md) /implement # (select docs/specs/UnitsFeature.md)
        ```
        * *(Note: `/implement` is a hypothetical slash command representing agent execution based on instructions. The actual command or method might differ. You might need to use `/new` or another available agent command and rely on the instructions within the referenced file to guide it.)*
        * Alternatively, if no specific command exists, try pasting the *content* of `implement_feature_workflow.md` directly into the chat, followed by the reference to the spec file: `Implement the feature described in # (select docs/specs/UnitsFeature.md) following these instructions: [Paste content of implement_feature_workflow.md here]`
    4.  **Observe and Verify:**
        * Monitor Copilot's actions. Does it indicate it's following the workflow?
        * Check the file system. Are the expected files (`WeatherApp.java`, `WeatherData.java`) modified?
        * Examine the changes. Does the code reflect the requirements (argument parsing, placeholders)? Does it seem to adhere to the logging/error handling guidelines from custom instructions?
        * Does Copilot generate the summary report requested in the workflow?

---
