const fs = require('fs');

// 1x1 transparent PNG base64
const pngBase64 = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNkYAAAAAYAAjCB0C8AAAAASUVORK5CYII=";
const pngBuffer = Buffer.from(pngBase64, 'base64');

// Create minimal SVG
const svgContent = '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 100 100"><rect width="100" height="100" fill="black"/></svg>';

fs.writeFileSync('public/pwa-192x192.png', pngBuffer);
fs.writeFileSync('public/pwa-512x512.png', pngBuffer);
fs.writeFileSync('public/favicon.ico', pngBuffer);
fs.writeFileSync('public/apple-touch-icon.png', pngBuffer);
fs.writeFileSync('public/masked-icon.svg', svgContent);
