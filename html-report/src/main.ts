import { createApp } from 'vue'
import './style.css'
import App from './App.vue'

const rootProps = { initialCount: globalThis.initialCount };

createApp(App, rootProps).mount('#app');
