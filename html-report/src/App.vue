<script setup lang="ts">
import { ref } from 'vue';
import ExecutionDetails from './components/details/ExecutionDetails.vue';
import TestNodeDetails from './components/details/TestNodeDetails.vue';
import Selection from './Selection';
import StatusBar from './components/header/StatusBar.vue';
import TestExecution from './TestExecution';
import Sidebar from "./components/sidebar/Sidebar.vue";

const props = defineProps<{ executions: TestExecution[] }>()
const selection = ref<Selection | undefined>();
selection.value = TestExecution.initialSelection(props.executions)
</script>

<template>
  <main class="flex flex-col h-screen bg-white dark:bg-black text-black dark:text-white">
    <StatusBar :executions="executions" />
    <div class="flex flex-1 overflow-hidden">
      <Sidebar :executions="executions" v-model:selection="selection" class="overflow-auto w-1/2 lg:w-1/3 2xl:w-1/4 border-r border-neutral-200 dark:border-neutral-700 h-full" />
      <div class="flex-1 overflow-auto">
        <ExecutionDetails v-if="(selection?.item instanceof TestExecution)" :execution="selection?.item" />
        <TestNodeDetails :execution="selection?.execution" :node="(selection?.item as TestNodeData)"
          v-model:selection="selection" v-else-if="selection" />
      </div>
    </div>
  </main>
</template>
