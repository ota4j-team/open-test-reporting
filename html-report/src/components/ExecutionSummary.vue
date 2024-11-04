<script setup lang="ts">
import { computed } from 'vue';
import { useI18n } from 'vue-i18n';
import TestResultStatusIcon from './TestResultStatusIcon.vue';
import TestExecution from '../TestExecution';

const { t } = useI18n()
const props = defineProps<{ execution: TestExecution }>()
const overallStatus = computed(() => props.execution.overallStatus())
function formattedCount(key: string, count?: number): string[] {
  return count ? [t(key, { count: count }, count)] : []
}
const summaryMessage = computed(() => {
  const statusCount = props.execution.statusCount()
  return [
    t('executionSummary.testCount', { count: props.execution.size() }, props.execution.size()),
    ...formattedCount('executionSummary.failed', statusCount.get('FAILED')),
    ...formattedCount('executionSummary.aborted', statusCount.get('ABORTED')),
    ...formattedCount('executionSummary.skipped', statusCount.get('SKIPPED')),
  ].join(', ')
})
</script>

<template>
  <div class="p-px px-1 inline-flex">
    <TestResultStatusIcon :status="overallStatus" :color="'white'" />
    <span class="ml-1 mt-px font-bold self-center">{{ summaryMessage }}</span>
  </div>
</template>
