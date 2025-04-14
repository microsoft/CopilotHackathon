import fs from 'fs';
import path from 'path';
import { generateAsciiArtSvg } from './generateAsciiArtSvg.js';

export default function asciiArtToSvgPlugin() {
  return {
    name: 'ascii-art-to-svg',
    enforce: 'pre', // Ensure this plugin runs before other plugins
    transform(code, id) {
      if (id.endsWith('.atxt')) {
        // Generate the SVG string from the .atxt content
        const svgContent = generateAsciiArtSvg(code, 'ascii-art-svg');
        // Return the SVG string as a JavaScript module
        return `export default ${JSON.stringify(svgContent)};`;
      }
    },
  };
}