<script setup lang="ts">
import { ref } from 'vue';
import { ResizableConfig, vResizable } from 'vue-resizables'
import ExecutionDetails from './components/ExecutionDetails.vue';
import ExecutionTree from './components/ExecutionTree.vue';
import TestNodeDetails from './components/TestNodeDetails.vue';
import Selection from './Selection';
import StatusBar from './components/StatusBar.vue';
import TestExecution from './TestExecution';
import ToolBar from './components/ToolBar.vue';

const props = defineProps<{ executions: TestExecution[] }>()
const selection = ref<Selection | undefined>();
selection.value = TestExecution.initialSelection(props.executions)
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
</script>

<template>
  <main class="flex flex-col h-screen bg-white dark:bg-black text-black dark:text-white">
    <StatusBar :executions="executions" />
    <div class="flex flex-1 overflow-hidden">
      <div v-resizable="resizeConfig"
        class="resize-x bg-neutral-100 dark:bg-neutral-800 w-1/2 lg:w-1/3 2xl:w-1/4 min-w-80 overflow-auto text-sm border-r border-neutral-200 dark:border-neutral-700">
        <div class="w-full h-full">
          <ToolBar :executions="executions" />
          <ExecutionTree :executions="executions" v-model:selection="selection" class="ml-1.5 mt-2.5" />
        </div>
      </div>
      <div class="flex-1 overflow-auto">
        <ExecutionDetails v-if="(selection?.item instanceof TestExecution)" :execution="selection?.item" />
        <TestNodeDetails :execution="selection?.execution" :node="(selection?.item as TestNodeData)"
          v-model:selection="selection" v-else-if="selection" />
      </div>
    </div>
  </main>
</template>

<style scoped></style>
