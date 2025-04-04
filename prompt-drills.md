## GitHub Copilot Hackathon Extended Drilling Exercises (Up to 75 Minutes - Adjust as Needed)

**EXPERIMENTAL**

This extended set of exercises will further explore GitHub Copilot's capabilities within VS Code, incorporating more advanced features. Aim to spend roughly 1-2 minutes on simpler exercises and 2-4 minutes on more complex ones.

**Setup:**

- [ ] Open Visual Studio Code.
- [ ] Ensure you have the GitHub Copilot extension installed and are logged in.
- [ ] Open an existing code project (or create a more substantial one with multiple files, functions, and potentially some comments and intentional errors for better exploration).
- [ ] **(Optional for Diagram Exercises)** Install a VS Code extension for rendering PlantUML or Mermaid diagrams (e.g., "PlantUML" by jebbs or "Markdown Preview Enhanced" which supports Mermaid).

**Exercises:**

- [ ] **Exploring Basic In-line Suggestions:**
    * Start typing common code structures. Observe and accept/ignore suggestions.

- [ ] **Getting Alternative In-line Suggestions:**
    * Type some code and wait for an in-line suggestion.
    * Press `Ctrl+Enter` (or `Cmd+Enter` on macOS) to open the Copilot window with alternative suggestions.
    * Browse through the alternatives and select one you like.
    * **Goal:** Learn how to access and choose from multiple in-line suggestions.

- [ ] **Understanding Codebase with `@workspace /explain` (Revisited):**
    * Use `@workspace /explain` on a more intricate section of your code.

- [ ] **Generating Diagram Code with `@workspace` (More Specific):**
    * In the Copilot Chat panel, type: `@workspace Generate PlantUML code for a class diagram showing the relationships between the User, Order, and Product models.` (or use Mermaid syntax if preferred).
    * Copy the generated PlantUML/Mermaid code.
    * (If you have a diagram extension installed) Open a new file, paste the code, and see the rendered diagram.
    * **Goal:** Understand how to use `@workspace` to generate code that *describes* diagrams, which can then be rendered using other tools.

- [ ] **Generating Unit Tests with `#selection` (Multiple Scenarios):**
    * Select different types of code (e.g., a function with conditional logic, a class method) and use `#selection write unit tests`.
    * **Goal:** See how test generation adapts to different code structures.

- [ ] **Targeting Codebase Understanding with `@workspace #folder /explain` (Multiple Folders):**
    * Try using `@workspace #folder` with different folders in your project to understand their respective roles.
    * **Optional Extension:** You could ask Copilot to generate PlantUML or Mermaid diagram code to visualize components within a specific folder.

- [ ] **Referencing a Specific File with `@workspace #file /explain` (Different File Types):**
    * Use `@workspace #file` to ask questions about different types of files in your project (e.g., configuration files, data structures).

- [ ] **Referencing a Specific Function with `@workspace #sym /explain` (Complex Functions):**
    * Select a function with more complex logic or multiple nested calls and use `@workspace #sym /explain`.

- [ ] **Generating Documentation with Selection (Different Levels of Detail):**
    * Select code and try prompts like `Write a concise documentation summary for #selection` and `Write detailed JSDoc-style documentation for #selection`.
    * **Goal:** Explore different levels of documentation generation.

- [ ] **Generating Tests with Selection (Edge Cases):**
    * Select a function and try prompting `Generate unit tests for #selection including edge cases and error handling.`.
    * **Goal:** See if Copilot can anticipate and generate tests for less common scenarios.

- [ ] **Reviewing Code with Selection (Specific Focus):**
    * Select code and try prompts like `Review #selection for potential security vulnerabilities.` or `Review #selection for code style consistency.`.
    * **Goal:** Learn to guide the code review process with specific concerns.

- [ ] **Using `#terminalLastCommand` for Contextual Help:**
    * Open your VS Code terminal and execute a command (e.g., `git status`, `ls -l`).
    * In the chat, type: `#terminalLastCommand What was the output of the last command and what does it indicate?`.
    * **Goal:** See how terminal output can be used for debugging assistance.

- [ ] **Discussing VS Code with `@vscode` (Settings and Features):**
    * Ask `@vscode` about specific settings (e.g., "How do I enable word wrap?") or features (e.g., "Explain how the debugger works in VS Code.").

- [ ] **Generating Code with In-line Chat (Specific Task):**
    * In a code file, type a comment describing a small code snippet you want to generate (e.g., `// Function to calculate the factorial of a number`).
    * Observe the in-line suggestions and accept a suitable one.
    * **Goal:** Practice using comments to guide in-line code generation.

- [ ] **Refactoring Code with In-line Chat using `/edit`:**
    * Select a piece of code that could be improved (e.g., a long function).
    * In the in-line chat (after selecting), type `/edit Make this function more concise using list comprehensions.` or `/edit Extract this logic into a separate function named 'helperFunction'.`.
    * **Goal:** Learn to use the `/edit` command for specific refactoring instructions.

- [ ] **Explaining Code with In-line Chat:**
    * Select a line or block of code you want to understand.
    * In the in-line chat (after selecting), type: `Explain this code.` or `What does this line do?`.
    * **Goal:** Quickly get explanations for unfamiliar code snippets.

- [ ] **Generating Code Snippets with Chat (More Complex):**
    * In the Copilot Chat, describe a more complex code snippet you need (e.g., "Write a Python function that reads data from a CSV file and returns a list of dictionaries.").
    * **Goal:** Practice using the chat for generating larger and more specific code blocks.

- [ ] **Asking Follow-up Questions in Chat:**
    * After Copilot provides a response in the chat, ask a follow-up question to refine the answer or explore related topics.
    * **Goal:** Understand the iterative nature of interacting with Copilot.

- [ ] **Using `@workspace` to Find Code:**
    * In the Copilot Chat, type: `@workspace Find all occurrences of the function named 'processData'.`.
    * **Goal:** Learn how to use `@workspace` for code search within your project.

- [ ] **Generating Code Based on Examples in Chat:**
    * Provide a small example of the code you want (e.g., "I want a function that takes a list of strings and returns a new list with the length of each string. For example, ['apple', 'banana'] should return [5, 6].").
    * Then ask Copilot to generate the function.
    * **Goal:** See how providing examples can guide code generation.

- [ ] **Using `#selection` for Code Transformation:**
    * Select a block of code and ask Copilot to transform it (e.g., `Convert #selection from JavaScript to Python.`, `Make #selection use arrow functions.`).
    * **Goal:** Explore basic code transformation capabilities.

- [ ] **Generating Comments for Existing Code:**
    * Select a block of uncommented code and ask Copilot to `Add comments to #selection explaining the logic.`.
    * **Goal:** See how Copilot can help improve code readability through comments.

- [ ] **Asking About Best Practices with `@workspace`:**
    * In the chat, ask: `@workspace What are some best practices for error handling in this project?` or `@workspace Suggest ways to improve the performance of this module.`.
    * **Goal:** Leverage `@workspace` for getting advice on code quality and best practices within your codebase.

- [ ] **Using In-line Chat for Quick Modifications with `/edit`:**
    * Select a variable name and use the in-line chat to ask: `/edit Rename this variable to 'itemCount'.`.
    * **Goal:** Practice using `/edit` for small, focused code modifications.

- [ ] **Exploring Different In-line Suggestion Styles:**
    * Type code in different styles (e.g., verbose vs. concise) and observe how Copilot's suggestions adapt.
    * **Goal:** Understand the influence of your coding style on Copilot's suggestions.

- [ ] **Using `@workspace` to Understand Project Structure:**
    * In the chat, ask: `@workspace Describe the overall architecture of this project.` or `@workspace What are the main modules and their dependencies?`.
    * **Goal:** Use `@workspace` to get a high-level understanding of your project's organization.

- [ ] **Generating Data Structures with Chat:**
    * In the chat, describe the data structure you need (e.g., "Generate a JSON object representing a customer with properties for name, email, and address.").
    * **Goal:** Practice generating data structures using natural language in the chat.

- [ ] **Asking for Explanations of Technical Concepts:**
    * Use the Copilot Chat to ask general programming questions (e.g., "What is the difference between synchronous and asynchronous programming?", "Explain the concept of recursion.").
    * **Goal:** Utilize Copilot as a learning resource for technical concepts.

- [ ] **Combining `#file` and `#sym` for Focused Questions:**
    * In the chat, type: `@workspace #file:<path/to/your/file.js> #sym:<name_of_a_function_in_that_file> What are the potential side effects of this function?`.
    * **Goal:** See how combining `#file` and `#sym` allows for very precise questions.

- [ ] **Experimenting with Creative Prompts:**
    * Try using more open-ended or creative prompts in the chat (e.g., "Write a short poem about this code.", "Suggest three alternative ways to implement this feature.").
    * **Goal:** Explore the more imaginative capabilities of Copilot.

- [ ] **Fixing Errors with `/fix`:**
    * Introduce a deliberate error into a line of code.
    * Select the line with the error.
    * In the in-line chat, type `/fix`. Review the suggested fix.
    * **Goal:** Learn how to use the `/fix` command to get error correction suggestions.

- [ ] **Generating Documentation In-line with `/doc`:**
    * Select a function in your code.
    * In the in-line chat, type `/doc`. Observe the generated documentation suggestion.
    * **Goal:** Understand how to quickly generate documentation using the `/doc` in-line command.

- [ ] **Understanding the Current Editor with `#currentEditor`:**
    * Open a code file you want to analyze.
    * In the Copilot Chat, type: `@workspace /explain #currentEditor What are the main functionalities implemented here?`.
    * **Goal:** Learn how to explicitly use `#currentEditor` to focus analysis on the currently open file.

- [ ] **Referring to the Previous Response with `#previousResponse`:**
    * Ask Copilot a question in the chat.
    * Then, ask a follow-up question that refers to the previous answer, for example: `#previousResponse Elaborate on the second point you made.`
    * **Goal:** Understand how to use `#previousResponse` for more natural and contextual conversations with Copilot.

- [ ] **Asking Language/Technology Specific Questions:**
    * If your project uses Python, ask: `What are some common performance pitfalls in Python?`. If it uses JavaScript, ask: `Explain the concept of closures in JavaScript.`.
    * **Goal:** See how Copilot can provide language and technology-specific information.

- [ ] **Using the "Ask a Question" Entrypoint:**
    * Simply type a question directly into the text box at the top of the Copilot Chat panel without using any specific commands initially (e.g., "How do I sort a list in Python?").
    * **Goal:** Recognize the basic way to interact with Copilot through natural language.

- [ ] **Using the In-line Chat Shortcut for Code Interaction:**
    * Select a small block of code in your editor.
    * Press `Command + i` (macOS) or `Ctrl + i` (Windows/Linux) to open the in-line chat. You should see `#selection` automatically included in the prompt.
    * In the chat, type: `Add a comment explaining what this code does.` or `Add a console log statement at the end of this block.`
    * Observe how Copilot provides suggestions within the editor based on your in-line chat instruction. Press `Tab` to accept or `Esc` to dismiss.
    * **Goal:** Learn how to quickly open the in-line chat with selected code using the keyboard shortcut to provide instructions for additions or modifications.

**Wrap-up:**

- [ ] Discuss which new commands and techniques were most insightful.
- [ ] Encourage participants to continue exploring the documentation and experimenting with different prompts.
- [ ] Emphasize the importance of providing clear and specific prompts for the best results.
