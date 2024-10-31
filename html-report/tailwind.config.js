/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
  ],
  darkMode: 'selector',
  theme: {
    extend: {},
  },
  plugins: [],
  safelist: [
    ...['red', 'green', 'yellow', 'sky']
      .flatMap(color => [`border-${color}-600`, `bg-${color}-500`, `text-${color}-600`, `dark:border-${color}-500`, `dark:bg-${color}-600`, `dark:border-${color}-500`, `dark:bg-${color}-600`]),
    'text-white',
  ],
}

