<script setup lang="ts">
import { computed } from 'vue';
import TestResultStatusIcon from './TestResultStatusIcon.vue';

const props = defineProps<{ testCount: number, status: string }>()
const color = computed(() => props.status === 'SUCCESSFUL' ? 'green' : 'red')
</script>

<template>
  <div class="h-10 p-2 border-b-2 text-white" :class="[`border-${color}-600`, `bg-${color}-500`]">
    <div class="cursor-pointer rounded p-px px-1 inline-flex">
      <TestResultStatusIcon :status="status" class="text-white" />
      <span class="ml-1">{{ $t('executionSummary.testCount', { count: testCount }, testCount) }}.</span>
    </div>
  </div>
</template>
