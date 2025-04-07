# Learning GitHub Copilot: Build a React Game with ASCII Art

This guide will help you learn how to use GitHub Copilot to create a project similar to this ASCII-art-based React game. Follow the steps below to build your own project while leveraging Copilot's AI-powered suggestions.

---

## Prerequisites

Before starting, ensure you have the following installed:

1. **Node.js** (v16 or higher)
2. **npm** (comes with Node.js)
3. **Visual Studio Code** with the **GitHub Copilot extension** installed.

---

## Step-by-Step Guide

### 1. **Set Up Your Project**

The `completesolution` folder in this project contains a partially implemented game you can use as an example. You can either build on it to add advanced features or start your own project using the steps below.

1. Open Visual Studio Code and create a new folder for your project.
2. Open the terminal in VS Code and run the following commands to initialize a Vite + React project:

   ```bash
   npm create vite@latest reactgame --template react
   cd reactgame
   npm install
   ```

3. Install the necessary dependencies for ESLint and React:

   ```bash
   npm install eslint @eslint/js eslint-plugin-react-hooks eslint-plugin-react-refresh globals --save-dev
   ```

4. Open the `package.json` file and configure the scripts section to include:

   ```json
   "scripts": {
     "dev": "vite",
     "build": "vite build",
     "lint": "eslint .",
     "preview": "vite preview"
   }
   ```

---

### 2. **Create ASCII Art Files**

1. Create a folder named `src/ascii-art` in your project.
2. Add `.atxt` files for your game assets (e.g., `ship.atxt`, `enemy.atxt`, `ammo.atxt`). Use ASCII art to define your game objects. For example, you can create simple designs like spaceships, enemies, or projectiles.

   ```atxt
   #ascii-art 10x10
       xx    
      xxxx   
     xxxxxx  
    xxxxxxxx
    x xxxx x 
   xx xxxx xx
   xx x  x xx
    x xxxx x 
     xxxxxx  
       xx
   ```

3. Use Copilot to help you generate ASCII art by typing comments like `// ASCII art for a spaceship` and letting Copilot suggest designs.

---

### 3. **Write a Plugin to Convert ASCII Art**

The reason we are writing a plugin to generate SVGs from our custom text files (`.atxt`) is to enable automatic hot-reloading of changes on the webpage without needing to run conversion commands or manually refresh the page.

1. Create a `plugins` folder in your project.
2. Add a file named `asciiArtToSvgPlugin.js` to convert `.atxt` files into SVGs. Use Copilot to scaffold the plugin by typing comments like `// Plugin to convert ASCII art to SVG`.

   Example:

   ```js
   import fs from 'fs';
   import path from 'path';

   export default function asciiArtToSvgPlugin() {
     return {
       name: 'ascii-art-to-svg',
       transform(code, id) {
         if (id.endsWith('.atxt')) {
           // Copilot can help generate the SVG conversion logic here
           // Remember to mention about your ascii file and there is header also at the first line etc
         }
       },
     };
   }
   ```

3. Add the plugin to your `vite.config.js` file:

   ```js
   import asciiArtToSvgPlugin from './plugins/asciiArtToSvgPlugin.js';

   export default defineConfig({
     plugins: [react(), asciiArtToSvgPlugin()],
   });
   ```

---

### 4. **Build the Game UI**

1. Create a `src/App.jsx` file and use Copilot to scaffold the React components for your game. For example:

   ```jsx
   import React, { useState } from 'react';

   function App() {
     const [enemies, setEnemies] = useState([]);
     const [shipPosition, setShipPosition] = useState({ x: 0, y: 0 });

     return (
       <div>
         {/* Copilot can help you dynamically render game elements */}
       </div>
     );
   }

   export default App;
   ```

2. Use Copilot to generate logic for rendering ASCII art dynamically, handling game state, and detecting collisions.

---

### 5. **Style the Game**

1. Create a `src/index.css` file and use Copilot to help you write CSS for your game. For example:

   ```css
   body {
     margin: 0;
     display: flex;
     justify-content: center;
     align-items: center;
     background-color: #242424;
   }
   ```

2. Use Copilot to suggest styles for buttons, animations, and responsive layouts.

---

### 6. **Run and Test Your Game**

1. Start the development server:

   ```bash
   npm run dev
   ```

2. Open the game in your browser and test the functionality. Use Copilot to debug and improve your code as needed.

---

### 7. **Iterate and Expand**

1. Add new features to your game, such as:

   - Player controls for moving the ship.
   - Shooting mechanics.
   - Enemy movement and spawning.

2. Use Copilot to help you write the logic for these features.

---

### 8. **Share Your Project**

1. Push your project to GitHub.
2. Write a README file (like this one) to document your project and guide others.

---

## Tips for Using Copilot Effectively

- **Write clear comments**: Describe what you want to achieve, and Copilot will suggest relevant code.
- **Iterate on suggestions**: If Copilot's suggestion isn't perfect, refine it or try rephrasing your comment.
- **Learn from the code**: Use Copilot's suggestions as a learning tool to understand new concepts and patterns.

---

By following this guide, you'll not only build a fun React game but also learn how to use GitHub Copilot to accelerate your development process. Happy coding!
