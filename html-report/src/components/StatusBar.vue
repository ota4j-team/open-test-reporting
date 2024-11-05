<script setup lang="ts">
import { computed } from 'vue';
import TestExecution from '../TestExecution';
import testResultStatusColor from '../TestResultStatus';
import ExecutionSummary from './ExecutionSummary.vue';
import DarkModeSwitch from './DarkModeSwitch.vue';

const { executions } = defineProps<{ executions: TestExecution[] }>()
const overallStatus = computed(() => TestExecution.overallStatus(executions))
const color = computed(() => testResultStatusColor(overallStatus.value))
</script>

<template>
  <div class="flex flex-row h-10 p-2 border-b-2 text-white z-50"
    :class="[`border-${color}-600`, `bg-${color}-500`, `dark:border-${color}-500`, `dark:bg-${color}-600`]">
    <ExecutionSummary :executions="executions" class="grow" />
    <DarkModeSwitch />
  </div>
</template>