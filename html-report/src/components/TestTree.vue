<script setup lang="ts">
import TestExecution from '../TestExecution';
import TestNode from './TestNode.vue';

const selectedNode = defineModel('selectedNode')
defineProps<{ execution: TestExecution, roots: TestNode[] }>()
</script>

<template>
  <ul v-if="roots.length" class="pl-3">
    <li v-for="root in roots" :key="root.id">
      <TestNode :execution="execution" :node="root" v-model:selectedNode="selectedNode"/>
    </li>
  </ul>
</template>
