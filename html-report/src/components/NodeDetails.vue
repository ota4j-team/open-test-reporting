<script setup lang="ts">
import { computed } from 'vue';
import Section from './Section.vue';
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
    <Section :section="section" />
  </div>
</template>
