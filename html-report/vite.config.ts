import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import {viteSingleFile} from "vite-plugin-singlefile"

// https://vitejs.dev/config/
export default defineConfig(({command}) => {
    if (command === 'serve') {
        return {
            plugins: [vue()],
        }
    } else { // command === 'build'
        return {
            plugins: [vue(), viteSingleFile()],
        }
    }
})
