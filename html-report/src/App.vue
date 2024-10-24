<script setup lang="ts">
import ExecutionSummary from './components/ExecutionSummary.vue'
import TestTree from './components/TestTree.vue';

defineProps<{ testCount: number, roots: Array<TestNode> }>()
</script>

<template>
  <main class="flex flex-col h-screen">
    <div class="flex bg-green-600 h-10 p-2 border-b-4 border-green-700 text-white">
      <ExecutionSummary :testCount="testCount" />
    </div>
    <div class="flex flex-1 overflow-hidden">
      <div class="flex bg-gray-50 w-1/4 min-w-80 p-4 overflow-y-auto text-sm">
        <TestTree :roots="roots"/>
      </div>
      <div class="flex flex-1 flex-col">
        <div class="flex flex-1 overflow-y-auto p-4">
          <p>Content</p>
        </div>
      </div>
    </div>
  </main>
</template>

<style scoped>
</style>
