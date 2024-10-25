<script setup lang="ts">
defineProps<{ node: TestNode }>()
</script>

<template>
  <h2 class="text-xl mb-4">{{ node.name }}</h2>
  <pre class="text-xs rounded-lg shadow-inner p-4 h-1/2 max-h-96 border w-full overflow-scroll">{{ node }}</pre>
</template>

<style scoped>
</style>
