<script setup lang="ts">
import { computed } from "vue";
import TestExecution from "../common/TestExecution.ts";
import { testResultStatusBackgroundColorClasses } from "../common/TestResultStatus.ts";
import ExecutionSummary from "./ExecutionSummary.vue";
import DarkModeSwitch from "./DarkModeSwitch.vue";

const { executions } = defineProps<{ executions: TestExecution[] }>();
const overallStatus = computed(() => TestExecution.overallStatus(executions));
</script>

<template>
  <div
    class="flex flex-row h-10 p-2 border-b-2 text-white z-50"
    :class="testResultStatusBackgroundColorClasses(overallStatus)"
  >
    <ExecutionSummary :executions="executions" class="grow" />
    <DarkModeSwitch />
  </div>
</template>
