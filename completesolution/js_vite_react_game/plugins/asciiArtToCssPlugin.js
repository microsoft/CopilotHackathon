import fs from 'fs';
import path from 'path';
import { generateAsciiArtCss } from './generateAsciiArtCss.js';

export default function asciiArtToCssPlugin() {
  return {
    name: 'ascii-art-to-css',
    enforce: 'pre', // Ensure this plugin runs before other plugins
    handleHotUpdate({ file, server }) {
      if (file.endsWith('.atxt')) {
        const content = fs.readFileSync(file, 'utf-8');
        if (content.startsWith('#ascii-art')) {
          // Trigger a full page reload when a valid ASCII art file changes
          server.ws.send({ type: 'full-reload' });
        }
      }
    },
    transform(code, id) {
      if (id.endsWith('.atxt')) {
        // Extract the basename of the file (e.g., "enemy" from "enemy.atxt")
        const className = path.basename(id, '.atxt');

        // Generate CSS using the utility function
        const cssContent = generateAsciiArtCss(code, className);

        console.log(cssContent);

        // During development, inject CSS dynamically
        if (process.env.NODE_ENV === 'development') {
          return `
            const style = document.createElement('style');
            style.textContent = \`${cssContent}\`;
            document.head.appendChild(style);
          `;
        }

        // During production, write CSS to disk
        const cssFilePath = id.replace(/\.atxt$/, '.css');
        fs.writeFileSync(cssFilePath, cssContent, 'utf-8');

        // Return nothing for production builds
        return '';
      }
    },
  };
}