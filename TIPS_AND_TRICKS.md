# GitHub Copilot: Tips and Tricks

GitHub Copilot Chat is a powerful tool that can help you write code faster and more efficiently. Here are some tips and tricks to get the most out of it.

## 1.  Shortcuts

Here is the list of useful shortcuts.

### General

| Action                                        | Windows/Linux Shortcut | Mac Shortcut       | Command Name(Visual Studio Code)      |
|-----------------------------------------------|------------------------|--------------------|---------------------------------------|
| Open Command Palette                          | `Ctrl + Shift + P`     | `Cmd + Shift + P`  | `workbench.action.showCommands`       |
| Open Inline Chat in your code                 | `Ctrl + I`             | `Cmd + I`          | `github.copilot.inlineChat`           |
| Open Inline Chat pop up                       | `Ctrl + Shift + I`     | `Cmd + Shift + I`  | `github.copilot.inlineChatPopup`      |
| Open Copilot Chat                             | `Ctrl + Shift + P`, then type "Copilot Chat"| `Cmd + Shift + P`, then type "Copilot Chat" | `github.copilot.chat` |
| Using Copilot Chat history                    | `Scroll up/down`      | `Scroll up/down`
| Start new Copilot Chat in chat panel          | `Ctrl + L`             | `Ctrl + L`         | `github.copilot.startChat` |
| Toggle GitHub Copilot on/off                  | No default shortcut    | No default shortcut| `github.copilot.toggleCopilot`        |

### Code suggestions (Inline Chat)
| Action                                        | Windows/Linux Shortcut | Mac Shortcut       | Command Name(Visual Studio Code)      |
|-----------------------------------------------|------------------------|--------------------|---------------------------------------|
| Accept an inline suggestion                   | `Tab`                  | `Tab`              | `editor.action.inlineSuggest.commit`  |
| Dismiss an inline suggestion                  | `Esc`                  | `Esc`              | `editor.action.inlineSuggest.hide`    |
| Show next inline suggestion                   | `Alt + ]`              | `Option (⌥) + ]`   | `editor.action.inlineSuggest.showNext`|
| Show previous inline suggestion               | `Alt + [`              | `Option (⌥) + [`   | `editor.action.inlineSuggest.showPrevious`|
| Accept Next Word                              | `Control + →`          | `Command + →`      | `editor.action.inlineSuggest.acceptNextLine`|
| Trigger inline suggestion                     | `Alt + \`              | `Option (⌥) + \`   | `editor.action.inlineSuggest.trigger` |
| Open GitHub Copilot (additional suggestions in separate pane) | `Ctrl + Enter`         | `Ctrl + Return`    | `github.copilot.generate`             |


## 2. **Best Practices**

- **Be Specific**: The more specific your question, the better the response. Instead of asking "How do I sort an array?", ask "How do I sort an array of objects by a specific property in JavaScript?".
- **Provide Clear Context**: Include relevant code snippets or describe the problem context to help Copilot understand your query better. Ensure that the questions and code snippets are directly related to the issue you're addressing.
- **Keep the Conversation Focused**: Stick to the topic at hand to avoid confusing the context.
- **Remove Irrelevant Questions**: Clear out any off-topic or resolved questions to keep the chat concise.
- **Iterate and Refine**: If the initial response isn't perfect, refine your question or ask follow-up questions to get more accurate results.
- **Review and Validate**: Always review the generated code to ensure it meets your requirements and adheres to best practices.
- **Use Comments**: When asking for help with specific code, use comments to highlight the parts of the code you need assistance with. This makes it easier for Copilot to understand your request.



## 3. **Agents, slash commands and variables**
### Agents
Agents (or participants) can provide additional context to the chat, enhancing the relevance and accuracy of the responses. In the chat, press `@` to see the available agents for your IDE. For example, Visual Studio Code has:
| Agent                | Description                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| @workspace           | Manages and provides information about your current workspace.              |
| @vscode              | Offers assistance with Visual Studio Code-specific queries and commands.    |
| @terminal            | Helps with terminal commands and operations within the IDE.                 |

#### Examples
- `@workspace list files`
  - Lists all the files in your current workspace.
- `@vscode how to change the color theme`
  - Provides instructions on how to change the color theme in Visual Studio Code.
- `@terminal How to Build a Docker Image from Dockerfile`
  - Provides the command to build a Docker image from a Dockerfile within the terminal.

### Slash commands
Slash commands in GitHub Copilot Chat allow you to quickly perform actions or get information without typing out full commands. These commands can help streamline your workflow and make interactions with Copilot more efficient.

Using slash commands can save time and make your interactions with GitHub Copilot Chat more productive. Simply type `/` followed by the command you want to use and let Copilot handle the rest.

| Command                | Description                                                                 |
|------------------------|-----------------------------------------------------------------------------|
| `/help`                | Displays a list of available commands and their descriptions.               |
| `/explain`             | Provides an explanation for a selected piece of code.                       |
| `/tests`                | Generates test cases for the selected code.                                |
| `/fix`                 | Proposes a fix for the problems in the selected code.                       |
| `/clear`                 | Starts a new chat                       |

#### Examples

- `/help`
  - Shows a list of all available slash commands and their usage.
- `/explain function`
  - Provides a detailed explanation of the selected function.
- Highlight the code in the editor and type `/fix`  in the GitHub Copilot Chat input box and press Enter.
  - Fixes the selected code.

### Variables

GitHub Copilot Chat supports the use of variables to provide additional context and improve the accuracy of its responses. Here are some of the variables you can use:

| Variable               | Description                                                                 |
|------------------------|-----------------------------------------------------------------------------|
| `#file`                | Allows you to choose a specific file in the workspace. This can be useful when you want Copilot to focus on a particular file. |
| `#selection`           | Refers to the current selection in the active editor. Use this to focus Copilot's attention on a specific part of your code. |
| `#editor`              | Represents the visible source code in the active editor. This helps Copilot understand the broader context of your query. |
| `#terminalLastCommand` | The last command executed in the active terminal. Useful for debugging or understanding terminal output. |
| `#terminalSelection`   | The current selection in the active terminal. Helps Copilot provide context-specific assistance for terminal commands. |

#### Examples
- `@workspace /tests #file` (+ select filename)
  - This prompt asks Copilot to generate unit tests for the specified file in your workspace.
- `@workspace /explain #selection`
  - This prompt asks Copilot to explain the selected code in the active editor.
- `@workspace /fix #selection`
  - This prompt asks Copilot to propose a fix for the selected code in the active editor.
- `@terminal /explain #terminalLastCommand`
  - This prompt asks Copilot to explain the last command executed in the active terminal.

## 5. Some tips
- ### No need to go outside of the IDE
  You can ask anything coding-related directly within the IDE, eliminating the need to use Google Search or visit stackoverflow.com. Use Copilot Chat as a learning tool. Ask it to explain concepts, provide examples, or suggest best practices. This can be a great way to improve your coding skills.  For example:
    - **"How do I perform a deep copy in JavaScript without using lodash?"**
    - **"What are the most popular frameworks for implementing web applications using Python?"**
    - **"Tell me about SQL injections?"**

- ### Use Clear Function Naming
  Clear and descriptive function names can significantly improve the relevance and accuracy of Copilot's suggestions.

- ### Using comments to help Copilot understand your requests better
  - Top-level comments provide context, clarify intent, and guide refactoring, helping Copilot understand the broader code context, generate relevant suggestions, and identify areas for optimization.
   ```javascript
   # This script processes user data and generates a report.
   # It reads data from a CSV file, filters the data based on specific criteria,
   # and outputs the results to a new CSV file.
   ```
   - You can use comments to ask Copilot for help with specific parts of your code. This can be particularly useful when you need assistance with complex logic or debugging. Here’s an example:
   ```python
   // This function reads a file and returns its content
   def read_file(file_path):
    with open(file_path, 'r') as file:
        content = file.read()
    # Copilot, can you add error handling for file operations?
    return content
    ```

- ### **Refactoring and optimizing code**
  Highlight the code in editor and ask in chat e.g.:
  - *"Refactor this code to multiple methods"*
  - *"Optimize this code"*
  - *"Is this code secure"*

- ### **Copilot can often understand and correct minor typos**
  No need to spend time correcting small typos, e.g. *"If you write funciton instead of function"*, Copilot might still understand and provide the correct suggestion.