<script setup lang="ts">
defineProps<{ node: TestNode }>()
</script>

<template>
  <span>{{ node.name }}</span>
  <ul v-if="node.child">
    <li v-for="c in node.child">
      <TestNode :node="c"/>
    </li>
  </ul>
</template>

<style scoped>
</style>
