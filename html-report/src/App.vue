<script setup lang="ts">
import { ref } from 'vue';
import { ResizableConfig, vResizable } from 'vue-resizables'
import ExecutionDetails from './components/ExecutionDetails.vue';
import ExecutionTree from './components/ExecutionTree.vue';
import NodeDetails from './components/NodeDetails.vue';
import Selection from './Selection';
import StatusBar from './components/StatusBar.vue';
import TestExecution from './TestExecution';

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
        class="resize-x bg-neutral-100 dark:bg-neutral-800 w-1/2 lg:w-1/3 2xl:w-1/4 min-w-80 px-2 py-4 overflow-auto text-sm border-r border-neutral-200 dark:border-neutral-700">
        <ExecutionTree :executions="executions" v-model:selection="selection" class="-ml-3 w-full h-full" />
      </div>
      <div class="flex-1 overflow-auto">
        <ExecutionDetails v-if="(selection?.item instanceof TestExecution)" :execution="selection?.item" />
        <NodeDetails :execution="selection?.execution" :node="(selection?.item as TestNode)"
          v-model:selection="selection" v-else-if="selection" />
      </div>
    </div>
  </main>
</template>

<style scoped></style>
