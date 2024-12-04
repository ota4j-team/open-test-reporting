<script setup lang="ts">
import {ResizableConfig, vResizable} from 'vue-resizables'
import ExecutionTree from "./ExecutionTree.vue";
import ToolBar from "./ToolBar.vue";
import TestExecution from "../../TestExecution.ts";
import Selection from "../../Selection.ts";
import {provide, reactive} from "vue";
import {NodeUi, RootUi} from "./TreeState.ts";
import {rootStoreKey} from "./keys.ts";

const selection = defineModel<Selection | undefined>('selection')
const props = defineProps<{ executions: TestExecution[] }>()

const resizeConfig: ResizableConfig = {
  edge: {
    right: true,
  },
  size: {
    min: {
      width: 320, // min-w-80
    }
  }
}

provide(rootStoreKey, reactive<RootUi>({
      nodes: props.executions
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
))
</script>

<template>
  <div v-resizable="resizeConfig"
       class="resize-x bg-neutral-100 dark:bg-neutral-800 min-w-80 text-sm">
    <ToolBar :executions="executions"/>
    <ExecutionTree :executions="executions" v-model:selection="selection" class="ml-1.5 mt-2.5"/>
  </div>
</template>
