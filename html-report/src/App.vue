<script setup lang="ts">
import ExecutionSummary from './components/ExecutionSummary.vue'
import TestTree from './components/TestTree.vue';

defineProps<{ testCount: number, roots: Array<TestNode> }>()
</script>

<template>
  <ExecutionSummary :testCount="testCount" />
  <TestTree :roots="roots"/>
</template>

<style scoped>
</style>
