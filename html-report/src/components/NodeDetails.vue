<script setup lang="ts">
import { computed } from 'vue';
import Section from './Section.vue';
import TestResultStatusIcon from './TestResultStatusIcon.vue';
import { Timer } from 'lucide-vue-next';

const props = defineProps<{ node: TestNode }>()
const color = computed(() => props.node.status === 'SUCCESSFUL' ? 'green' : 'red')
</script>

<template>
  <h2 class="text-xl mb-4">{{ node.name }}</h2>
  <div class="inline-flex mb-2 border-2 rounded-full px-2 py-1 mr-2" :class="[`border-${color}-600`, `bg-${color}-500`]">
    <TestResultStatusIcon :status="node.status" class="text-white" />
    <span class="ml-1 tracking-wide text-sm text-white font-bold">{{ node.status }}</span>
  </div>
  <div class="inline-flex mb-2 border-2 rounded-full px-2 py-1 border-gray-400 bg-gray-300">
    <Timer :size="16" :strokeWidth="3" class="self-center" />
    <span class="ml-1 tracking-wide text-sm font-bold">{{ node.duration }}</span>
  </div>
  <div v-for="section in node.sections">
    <Section :section="section" />
  </div>
</template>
