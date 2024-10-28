<script setup lang="ts">
import { computed } from 'vue';
import TestResultStatusIcon from './TestResultStatusIcon.vue';

const props = defineProps<{ node: TestNode }>()
const color = computed(() => props.node.status === 'SUCCESSFUL' ? 'green' : 'red')
</script>

<template>
  <h2 class="text-xl mb-4">{{ node.name }}</h2>
  <div class="inline-flex mb-2 border-2 rounded-full px-2 py-1" :class="[`border-${color}-600`, `bg-${color}-500`]">
    <TestResultStatusIcon :status="node.status" class="text-white" />
    <span class="ml-1 tracking-wide text-xs text-white font-bold">{{ node.status }}</span>
  </div>
  <div v-for="section in node.sections">
    <h3 class="text-lg font-bold mt-2 mb-1">{{ section.title }}</h3>
    <p v-if="section.type === 'empty'" class="text-sm text-gray-500">{{ section.content }}</p>
    <pre v-else class="text-xs rounded-lg shadow-inner p-4 h-1/2 max-h-96 border w-full overflow-scroll">{{ section.content }}</pre>
  </div>
</template>
