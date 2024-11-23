import { createApp, Reactive, reactive } from 'vue'
import { createI18n } from 'vue-i18n'

import './style.css'
import App from './App.vue'
import TestExecution from './TestExecution'

const rootProps = {
  executions: globalThis.testExecutions
    .map(it => new TestExecution(it)),
};

export type NodeUi = {
    collapsed: boolean
}

export type RootUi = {
    nodes: Record<string, NodeUi>
    collapseAll: () => void
    expandAll: () => void
    toggleNode: (id: string) => void
}

export const rootStore: Reactive<RootUi> = reactive({
        nodes: rootProps.executions
            .reduce((prev, current) => {
                return {
                    ...prev,
                    [current.id]: {
                        collapsed: false,
                    },
                    ...current.idsForNodesWithChildren().reduce((prev, current) => {
                        return {
                            ...prev,
                            [current]: {
                                collapsed: false,
                            }
                        }
                    }, {})
                }
            }, {} as Record<string, NodeUi>),

        collapseAll() {
            Object.keys(this.nodes).forEach((key) => {
                this.nodes[key].collapsed = true;
            });
        },
        expandAll() {
            Object.keys(this.nodes).forEach((key) => {
                this.nodes[key].collapsed = false;
            });
        },
        toggleNode(id: string) {
            this.nodes[id].collapsed = !this.nodes[id].collapsed;
        }

    }
)
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
      toolbar: {
        collapseAll: 'Collapse all',
        expandAll: 'Expand all',
      }
    }
  }
});

app.use(i18n);
app.mount('#app');
