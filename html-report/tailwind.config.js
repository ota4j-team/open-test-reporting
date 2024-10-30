/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {},
  },
  plugins: [],
  safelist: [
    ...['red', 'green', 'yellow', 'gray']
      .flatMap(color => [`border-${color}-600`, `bg-${color}-500`, `text-${color}-600`]),
    'text-white',
  ],
}

