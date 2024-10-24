<script setup lang="ts">
import TestNode from './TestNode.vue';

defineProps<{ roots: Array<TestNode> }>()
</script>

<template>
  <ul v-if="roots.length">
    <li v-for="root in roots">
      <TestNode :node="root"/>
    </li>
  </ul>
</template>

<style scoped>
</style>
