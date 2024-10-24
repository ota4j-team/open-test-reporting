<script setup lang="ts">
import ExecutionSummary from './components/ExecutionSummary.vue'
import NodeDetails from './components/NodeDetails.vue';
import TestTree from './components/TestTree.vue';
import { ref } from 'vue';

defineProps<{ testCount: number, roots: Array<TestNode> }>()
const selectedNode = ref(undefined as TestNode | undefined)
</script>

<template>
  <main class="flex flex-col h-screen">
    <div class="flex bg-green-600 h-10 p-2 border-b-4 border-green-700 text-white">
      <ExecutionSummary :testCount="testCount" />
    </div>
    <div class="flex flex-1">
      <div class="flex bg-gray-50 w-1/4 min-w-80 p-4 overflow-y-auto text-sm">
        <TestTree :roots="roots" v-model:selectedNode="selectedNode" class="-ml-6"/>
      </div>
      <div class="flex flex-1 flex-col">
        <div class="flex flex-1 overflow-y-auto p-4">
          <div>
            <NodeDetails :node="selectedNode" v-if="selectedNode"/>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>

<style scoped>
</style>
