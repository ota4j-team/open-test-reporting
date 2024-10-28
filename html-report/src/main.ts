import { createApp } from 'vue'
import { createI18n } from 'vue-i18n'

import './style.css'
import App from './App.vue'
import TestNodeTree from './TestNodeTree'

const rootProps = {
    tree: new TestNodeTree(
        globalThis.roots,
        new Map(Object.entries(globalThis.children)),
        globalThis.testNodes,
    ),
};

console.log(rootProps);

const app = createApp(App, rootProps);

const i18n = createI18n({
    messages: {
        en: {
            executionSummary: {
                testCount: 'There are no tests | There is 1 test | There are {count} tests'
            }
        }
    }
});

app.use(i18n);
app.mount('#app');
