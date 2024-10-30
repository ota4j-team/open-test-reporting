<script setup lang="ts">
import { computed } from 'vue';
import { useI18n } from 'vue-i18n';
import TestResultStatusIcon from './TestResultStatusIcon.vue';
import TestNodeTree from '../TestNodeTree';
import testResultStatusColor from '../TestResultStatus';

const { t } = useI18n()
const props = defineProps<{ tree: TestNodeTree }>()
const overallStatus = computed(() => props.tree.overallStatus())
const color = computed(() => testResultStatusColor(overallStatus.value))
function formattedCount(key: string, count?: number): string[] {
  return count ? [t(key, { count: count }, count)] : []
}
const summaryMessage = computed(() => {
  const statusCount = props.tree.statusCount()
  return [
    t('executionSummary.testCount', { count: props.tree.size() }, props.tree.size()),
    ...formattedCount('executionSummary.failed', statusCount.get('FAILED')),
    ...formattedCount('executionSummary.aborted', statusCount.get('ABORTED')),
    ...formattedCount('executionSummary.skipped', statusCount.get('SKIPPED')),
  ].join(', ')
})
</script>

<template>
  <div class="h-10 p-2 border-b-2 text-white" :class="[`border-${color}-600`, `bg-${color}-500`]">
    <div class="cursor-pointer rounded p-px px-1 inline-flex">
      <TestResultStatusIcon :status="overallStatus" :color="'white'" />
      <span class="ml-1 font-bold">{{ summaryMessage }}</span>
    </div>
  </div>
</template>
