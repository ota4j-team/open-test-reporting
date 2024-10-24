<script setup lang="ts">
import TestNode from './TestNode.vue';

const selectedNode = defineModel('selectedNode')
defineProps<{ roots: Array<TestNode> }>()
</script>

<template>
  <ul v-if="roots.length" class="list-disc list-inside pl-5">
    <li v-for="root in roots" class="pt-0.5">
      <TestNode :node="root" v-model:selectedNode="selectedNode"/>
    </li>
  </ul>
</template>

<style scoped>
</style>
