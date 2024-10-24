<script setup lang="ts">
defineProps<{ node: TestNode }>()
</script>

<template>
  <h2 class="text-xl mb-4">{{ node.name }}</h2>
  <div><pre class="text-xs rounded-lg shadow-inner p-4 h-128 w-1/2 overflow-scroll">{{ node }}</pre></div>
</template>

<style scoped>
</style>
