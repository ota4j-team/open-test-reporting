<script setup lang="ts">
import NodeDetails from './components/NodeDetails.vue';
import TestTree from './components/TestTree.vue';
import { ref } from 'vue';
import TestNodeTree from './TestNodeTree';
import StatusBar from './components/StatusBar.vue';

const props = defineProps<{ tree: TestNodeTree }>()
const selectedNode = ref(props.tree.initialSelection());
</script>

<template>
  <main class="flex flex-col h-screen bg-white dark:bg-black text-black dark:text-white">
    <StatusBar :tree="tree" />
    <div class="flex flex-1 overflow-hidden">
      <div class="bg-neutral-100 dark:bg-neutral-800 w-1/4 min-w-80 px-2 py-4 overflow-auto text-sm">
        <TestTree :tree="tree" :roots="tree.roots()" v-model:selectedNode="selectedNode" class="-ml-3"/>
      </div>
      <div class="flex-1 overflow-auto">
        <NodeDetails :tree="tree" :node="selectedNode" v-model:selectedNode="selectedNode" v-if="selectedNode" />
      </div>
    </div>
  </main>
</template>

<style scoped>
</style>
