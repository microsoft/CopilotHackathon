/**
 * Generates CSS from an .atxt file's content.
 * @param {string} atxtContent - The full content of the .atxt file, including the header.
 * @param {string} className - The class name to use in the generated CSS.
 * @returns {string} - The generated CSS string.
 * @throws {Error} - Throws an error if the size in the header is invalid.
 */
export function generateAsciiArtCss(atxtContent, className) {
  // Split the content into lines
  const lines = atxtContent.trim().split('\n');

  // Parse the header
  const header = lines[0];
  if (!header.startsWith('#ascii-art')) {
    throw new Error('Invalid .atxt file: Missing #ascii-art header.');
  }

  // Extract the size from the header (e.g., "10x10")
  const sizeMatch = header.match(/#ascii-art (\d+)x(\d+)/);
  if (!sizeMatch) {
    throw new Error('Invalid .atxt file: Missing or invalid size in header.');
  }
  const [_, width, height] = sizeMatch.map(Number);

  // Extract the ASCII art content
  const asciiArt = lines.slice(1);

  // Fill missing rows with blank lines to match the specified height
  const filledAsciiArt = Array.from({ length: height }, (_, y) => {
    return asciiArt[y] ? asciiArt[y].padEnd(width, ' ') : ' '.repeat(width);
  });

  // Validate the dimensions of the ASCII art
  for (const line of filledAsciiArt) {
    if (line.length !== width) {
      throw new Error(`Invalid .atxt file: Expected each row to have ${width} columns, but got "${line}".`);
    }
  }

  // Convert ASCII art to CSS box-shadow styles
  const boxShadow = filledAsciiArt
    .flatMap((line, y) =>
      line.split('').map((char, x) => {
        if (char === ' ') return null; // Skip empty spaces
        const color = char === '#' ? 'black' : 'gray'; // Define colors based on characters
        return `${x}px ${y}px 0 0 ${color}`;
      })
    )
    .filter(Boolean) // Remove null values
    .join(',\n      '); // Format for better readability

  // Generate the CSS content
  return `
    .${className} {
      width: 1px;
      height: 1px;
      box-shadow: ${boxShadow};
      transform: scale(10); /* Scale up the image */
      transform-origin: top left; /* Important for scaling */
    }
  `;
}