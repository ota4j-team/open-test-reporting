import {createApp, Reactive, reactive} from 'vue'
import {createI18n} from 'vue-i18n'

import './style.css'
import App from './App.vue'
import TestExecution from './TestExecution'
import VueEasyLightbox from "vue-easy-lightbox";

const rootProps = {
  executions: globalThis.testExecutions
    .map(it => new TestExecution(it)),
};

export type NodeUi = {
    collapsed: boolean
}

export type RootUi = {
    nodes: Record<string, NodeUi>
    showAborted: boolean
    showFailed: boolean
    showSkipped: boolean
    showSuccessful: boolean

    collapseAll: () => void
    expandAll: () => void
    toggleShowAborted: () => void
    toggleShowFailed: () => void
    toggleShowSkipped: () => void
    toggleShowSuccessful: () => void
    toggleNode: (id: string) => void

    isVisible: (statuses: string[]) => boolean
}

export const rootStore: Reactive<RootUi> = reactive<RootUi>({
        nodes: rootProps.executions
            .reduce((prev, execution) => {
                return {
                    ...prev,
                    [execution.id]: {
                        collapsed: false,
                    },
                    ...execution.nodesWithChildren().reduce((prev, node) => {
                        const statuses = execution.nodeStatuses(node);
                        let initiallyCollapsed = execution.parents(node).length > 1 && statuses.indexOf('FAILED') == -1;
                        return {
                            ...prev,
                            [node.id]: {
                                collapsed: initiallyCollapsed,
                            }
                        }
                    }, {})
                }
            }, {} as Record<string, NodeUi>),
        showAborted: true,
        showFailed: true,
        showSkipped: true,
        showSuccessful: true,

        toggleShowAborted() {
            this.showAborted = !this.showAborted;
        },

        toggleShowFailed() {
            this.showFailed = !this.showFailed;
        },

        toggleShowSuccessful() {
            this.showSuccessful = !this.showSuccessful;
        },

        toggleShowSkipped() {
            this.showSkipped = !this.showSkipped;
        },

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
        },

        isVisible(statuses: string[]): boolean {
            return statuses.length == 0 || statuses.filter(status => {
                switch (status) {
                    case 'SUCCESSFUL':
                        return this.showSuccessful;
                    case 'FAILED':
                        return this.showFailed;
                    case 'SKIPPED':
                        return this.showSkipped;
                    case 'ABORTED':
                        return this.showAborted;
                }
            }).length > 0;
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
        showAborted: 'Show aborted',
        showFailed: 'Show failed',
        showSkipped: 'Show skipped',
        showSuccessful: 'Show successful',
      }
    }
  }
});

app.use(i18n);
app.use(VueEasyLightbox);
app.mount('#app');
