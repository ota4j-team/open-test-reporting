<script setup lang="ts">
import NodeDetails from './components/NodeDetails.vue';
import ExecutionTree from './components/ExecutionTree.vue';
import ExecutionDetails from './components/ExecutionDetails.vue';
import { computed, ref } from 'vue';
import TestExecution from './TestExecution';
import StatusBar from './components/StatusBar.vue';

const props = defineProps<{ executions: TestExecution[] }>()
const execution = computed(() => props.executions[0]);
const selectedNode = ref<TestExecution | TestNode | undefined>();
selectedNode.value = props.executions[0].initialSelection()
</script>

<template>
  <main class="flex flex-col h-screen bg-white dark:bg-black text-black dark:text-white">
    <StatusBar :execution="execution" />
    <div class="flex flex-1 overflow-hidden">
      <div class="bg-neutral-100 dark:bg-neutral-800 w-1/4 min-w-80 px-2 py-4 overflow-auto text-sm">
        <ExecutionTree :executions="executions" v-model:selectedNode="selectedNode" class="-ml-3" />
      </div>
      <div class="flex-1 overflow-auto">
        <ExecutionDetails v-if="(selectedNode instanceof TestExecution)" :execution="execution" />
        <NodeDetails :execution="execution" :node="(selectedNode as TestNode)" v-model:selectedNode="selectedNode" v-else-if="selectedNode" />
      </div>
    </div>
  </main>
</template>

<style scoped>
</style>
