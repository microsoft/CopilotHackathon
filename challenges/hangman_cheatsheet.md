# GitHub Copilot Interaction Cheatsheet

This cheatsheet summarizes key ways to interact with GitHub Copilot in VS Code, based on the Simple Weather CLI exercises. Use this as a quick reference, especially when focusing on code generation.

---

## 1. Main Interaction Interfaces

* **Chat View:**
    * Primary panel for detailed questions, code generation, explanations.
    * Use **Participants (`@`)** and **Variables (`#`)** for context.
    * Use **Slash Commands (`/`)** for specific actions.
    * Modes like **"Ask"** (default), **"Edits"**, and **"Agent"** (via `/new`) available.
* **Inline Chat:**
    * Quick chat directly in the editor (`Cmd+I` / `Ctrl+I`) on selected code.
    * Great for fast explanations (`/explain`), documentation (`/doc`), or quick edits/fixes (`/fix`).
    * Review multiple suggestions: `Alt+]` / `Option+]` (Next), `Alt+[` / `Option+[` (Previous).

---

## 2. Providing Context (Crucial for Good Results!)

* **Participants (`@`):** Broad context. **Limit: 1 per prompt.**
    * `@workspace`: The entire project/workspace content. Good for general questions, brainstorming, and analysis in "Ask" mode.
    * `@vscode`: VS Code editor features, settings, tasks.
* **Variables (`#`):** Granular context. **Can be combined** with a participant. Use `#` + start typing (interactive select) or drag/drop files/folders.
    * `#file:path/to/YourFile.java`: Specific file content.
    * `#folder:path/to/your/folder`: Specific folder content.
    * `#selection`: Currently selected code in the editor.
    * `#sym:YourClassName` or `#sym:yourMethodName`: A specific class, method, or variable symbol.
    * `#usage:YourSymbolName`: Find where a symbol is used (implementations, calls).
    * `#codebase`: Workspace context (variable form, often implicitly used or needed for **Edits** / **Agent (`/new`)** modes).
    * `#changes`: Current staged/unstaged Git changes.
    * `#terminalLastCommand`: The last command run in the integrated terminal.
    * `#terminalSelection`: Text selected in the integrated terminal.
    * `#fetch:URL`: Content from a web URL.

* **`@workspace` vs `#codebase`:**
    * Both provide project-wide context.
    * `@workspace` is a *Participant* (use in Ask mode, subject to 1-participant limit).
    * `#codebase` is a *Variable* (often needed for **Edits** mode or **`/new`** command, doesn't conflict with participant limit).

---

## 3. Directing Copilot's Actions

* **Code Completion:** Automatic suggestions as you type. Press `Tab` to accept.
* **Slash Commands (`/`):** Use in Chat View or Inline Chat.
    * `/explain`: Ask for explanations of code (`#selection`, `#file`), concepts, errors (`#terminalSelection`), commands (`#terminalLastCommand`).
    * `/tests`: Generate unit tests (provide context like `#file:TestFile.java #file:SourceFile.java`).
    * `/fix`: Suggest fixes for selected code (`#selection`) or based on error messages.
    * `/new`: Generate new files/scaffolding (Agent mode, often needs `#codebase` or specific instructions).
    * `/doc`: Generate documentation (usually for `#selection`).
* **"Edits" Mode (Chat View):**
    * Select code in the editor.
    * Choose "Edits" mode in the Chat View input dropdown.
    * Type natural language instructions for refactoring or modification (e.g., "Extract this logic into a new private method", "Add error handling for...")
    * Review the proposed diff and apply.
* **Agent Mode (`/new`):**
    * Used to create new files/components.
    * Provide detailed instructions on the structure and content.
    * Often requires broader context (`#codebase`).

---

## 4. Guiding Copilot's Behavior

* **Custom Instructions:**
    * Create `.github/copilot-instructions.md` in your project root.
    * Define rules, patterns, or style guides (e.g., logging practices, preferred libraries).
    * Copilot automatically uses these instructions to tailor suggestions for the workspace.

---

## 5. Key Activities for Code Generation Focus

* **Scaffolding:** Use `/new #codebase` to create new classes/files based on requirements.
* **Implementing Methods/Logic:**
    * Use **Code Completion** for line-by-line help.
    * Use **Ask Mode** (`@workspace` or `#file` context) to ask "How do I implement...?" or "Show me code to...".
    * Use **Edits Mode** on method stubs or existing code to add/modify logic based on instructions.
* **Generating Tests:** Use `/tests` with file context (`#file:Test.java #file:Source.java`).
* **Refactoring:** Use **Edits Mode** on selected code with instructions like "Refactor...", "Improve...", "Simplify...".
* **Adding Features:** Combine brainstorming (`@workspace`), planning (`@workspace /explain steps for feature X`), and implementation using Edits/Ask/Completion.
* **Documentation:** Use `/doc #selection` or Inline Chat (`/doc`).

---