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

const app = createApp(App, rootProps);

const i18n = createI18n({
    messages: {
        en: {
            executionSummary: {
                testCount: 'No tests | 1 test/container | {count} tests/containers',
                failed: '1 failed | {count} failed',
                aborted: '1 aborted | {count} aborted',
                skipped: '1 skipped | {count} skipped',
            }
        }
    }
});

app.use(i18n);
app.mount('#app');
