<script setup lang="ts">
import NodeDetails from './components/NodeDetails.vue';
import ExecutionTree from './components/ExecutionTree.vue';
import ExecutionDetails from './components/ExecutionDetails.vue';
import { ref } from 'vue';
import TestExecution from './TestExecution';
import StatusBar from './components/StatusBar.vue';
import Selection from './Selection';

const props = defineProps<{ executions: TestExecution[] }>()
const selection = ref<Selection | undefined>();
selection.value = TestExecution.initialSelection(props.executions)
</script>

<template>
  <main class="flex flex-col h-screen bg-white dark:bg-black text-black dark:text-white">
    <StatusBar :executions="executions" />
    <div class="flex flex-1 overflow-hidden">
      <div class="bg-neutral-100 dark:bg-neutral-800 w-1/4 min-w-80 px-2 py-4 overflow-auto text-sm">
        <ExecutionTree :executions="executions" v-model:selection="selection" class="-ml-3" />
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
