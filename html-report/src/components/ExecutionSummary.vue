<script setup lang="ts">
import { computed } from 'vue';
import { useI18n } from 'vue-i18n';
import TestResultStatusIcon from './TestResultStatusIcon.vue';
import TestExecution from '../TestExecution';

const { t } = useI18n()
const { executions } = defineProps<{ executions: TestExecution[] }>()
const overallStatus = computed(() => TestExecution.overallStatus(executions))
function formattedCount(key: string, count?: number): string[] {
  return count ? [t(key, { count: count }, count)] : []
}
const summaryMessage = computed(() => {
  const statusCount = TestExecution.statusCount(executions)
  const testCount = executions.map(e => e.size()).reduce((sum, current) => sum + current)
  return [
    t('executionSummary.testCount', { count: testCount }, testCount),
    ...formattedCount('executionSummary.failed', statusCount.get('FAILED')),
    ...formattedCount('executionSummary.aborted', statusCount.get('ABORTED')),
    ...formattedCount('executionSummary.skipped', statusCount.get('SKIPPED')),
  ].join(', ')
})
</script>

<template>
  <div class="p-px px-1 inline-flex">
    <TestResultStatusIcon :status="overallStatus" color="text-white" />
    <span class="ml-1 mt-px font-bold self-center">{{ summaryMessage }}</span>
    <span v-if="executions.length > 1" class="ml-1 mt-px font-bold self-center text-white/60 dark:text-white/50">
      ({{ $t('executionSummary.execution', { count: executions.length }, executions.length) }})
    </span>
  </div>
</template>
