<script setup lang="ts">
import { computed } from 'vue';
import { useI18n } from 'vue-i18n';
import TestResultStatusIcon from './TestResultStatusIcon.vue';
import TestNodeTree from '../TestNodeTree';

const { t } = useI18n()
const props = defineProps<{ tree: TestNodeTree }>()
const overallStatus = computed(() => props.tree.overallStatus())
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
  <div class="p-px px-1 inline-flex">
    <TestResultStatusIcon :status="overallStatus" :color="'white'" />
    <span class="ml-1 mt-px font-bold self-center">{{ summaryMessage }}</span>
  </div>
</template>
