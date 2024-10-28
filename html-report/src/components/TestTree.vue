<script setup lang="ts">
import TestNodeTree from '../TestNodeTree';
import TestNode from './TestNode.vue';

const selectedNode = defineModel('selectedNode')
defineProps<{ tree: TestNodeTree, roots: TestNode[] }>()
</script>

<template>
  <ul v-if="roots.length" class="pl-3">
    <li v-for="root in roots" class="pt-0.5" :key="root.id">
      <TestNode :tree="tree" :node="root" v-model:selectedNode="selectedNode"/>
    </li>
  </ul>
</template>
