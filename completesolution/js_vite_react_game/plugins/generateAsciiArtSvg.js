/**
 * Generates an SVG string from an .atxt file's content.
 * @param {string} atxtContent - The full content of the .atxt file, including the header.
 * @param {string} className - The class name to use in the generated SVG.
 * @returns {string} - The generated SVG string.
 * @throws {Error} - Throws an error if the size in the header is invalid.
 */
export function generateAsciiArtSvg(atxtContent, className) {
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

  // Generate the SVG `<rect>` elements
  const rects = filledAsciiArt
    .flatMap((line, y) =>
      line.split('').map((char, x) => {
        if (char === ' ') return null; // Skip empty spaces
        const color = char === '#' ? 'black' : 'gray'; // Define colors based on characters
        return `<rect x="${x}" y="${y}" width="1" height="1" fill="${color}" />`;
      })
    )
    .filter(Boolean) // Remove null values
    .join('\n  '); // Format for better readability

  // Generate the full SVG content
  return `
    <svg
      xmlns="http://www.w3.org/2000/svg"
      viewBox="0 0 ${width} ${height}"
      width="${width * 10}" // Scale width by 20x
      height="${height * 10}" // Scale height by 20x
      class="${className}"
    >
      ${rects}
    </svg>
  `;
}