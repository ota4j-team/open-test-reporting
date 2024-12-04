import { createApp } from "vue";
import { createI18n } from "vue-i18n";

import "./style.css";
import App from "./App.vue";
import TestExecution from "./components/common/TestExecution.ts";
import VueEasyLightbox from "vue-easy-lightbox";

const app = createApp(App, {
  executions: globalThis.testExecutions.map((it) => new TestExecution(it)),
});

app.use(
  createI18n({
    messages: {
      en: {
        executionSummary: {
          testCount: "No tests | 1 test/container | {count} tests/containers",
          failed: "1 failed | {count} failed",
          aborted: "1 aborted | {count} aborted",
          skipped: "1 skipped | {count} skipped",
          execution: "in 1 execution | in { count } executions",
        },
        clipboard: {
          copy: "Copy to clipboard",
        },
        duration: {
          hours: "{count} h",
          minutes: "{count} m",
          seconds: "{count} s",
          millis: "{count} ms",
        },
        toolbar: {
          collapseAll: "Collapse all",
          expandAll: "Expand all",
          showAborted: "Show aborted",
          showFailed: "Show failed",
          showSkipped: "Show skipped",
          showSuccessful: "Show successful",
        },
      },
    },
  }),
);

app.use(VueEasyLightbox);

app.mount("#app");
