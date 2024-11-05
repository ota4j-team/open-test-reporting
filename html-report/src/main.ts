import { createApp } from 'vue'
import { createI18n } from 'vue-i18n'

import './style.css'
import App from './App.vue'
import TestExecution from './TestExecution'

const rootProps = {
  executions: globalThis.testExecutions
    .map(it => new TestExecution(it)),
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
        execution: 'in 1 execution | in { count } executions',
      },
      clipboard: {
        copy: 'Copy to clipboard',
      },
      duration: {
        hours: '{count} h',
        minutes: '{count} m',
        seconds: '{count} s',
        millis: '{count} ms',
      },
    }
  }
});

app.use(i18n);
app.mount('#app');
