<script setup lang="ts">
import { computed } from 'vue';
import TestNodeTree from '../TestNodeTree';
import testResultStatusColor from '../TestResultStatus';
import ExecutionSummary from './ExecutionSummary.vue';
import DarkModeSwitch from './DarkModeSwitch.vue';

const props = defineProps<{ tree: TestNodeTree }>()
const overallStatus = computed(() => props.tree.overallStatus())
const color = computed(() => testResultStatusColor(overallStatus.value))
</script>

<template>
  <div class="flex flex-row h-10 p-2 border-b-2 text-white" :class="[`border-${color}-600`, `bg-${color}-500`, `dark:border-${color}-500`, `dark:bg-${color}-600`]">
    <ExecutionSummary :tree="tree" class="grow" />
    <DarkModeSwitch />
  </div>
</template>