import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";
import { viteSingleFile } from "vite-plugin-singlefile";
import tailwindcss from "@tailwindcss/vite";

// https://vitejs.dev/config/
export default defineConfig(({ command }) => {
  const defaultPlugins = [vue(), tailwindcss()];
  if (command === "serve") {
    return {
      plugins: defaultPlugins,
    };
  } else {
    // command === 'build'
    return {
      plugins: [...defaultPlugins, viteSingleFile()],
    };
  }
});
