import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import asciiArtToSvgPlugin from './plugins/asciiArtToSvgPlugin.js'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react(), asciiArtToSvgPlugin()],
})
