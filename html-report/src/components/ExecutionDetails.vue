<script setup lang="ts">
import TestExecution from '../TestExecution';
import DetailsHeader from './DetailsHeader.vue';
import DetailsSections from './DetailsSections.vue';
import DurationLabel from './DurationLabel.vue';

defineProps<{ execution: TestExecution }>()
</script>

<template>
  <DetailsHeader :title="execution.name">
    <template #below>
      <div class="mt-3">
        <DurationLabel :millis="execution.durationMillis" />
      </div>
    </template>
  </DetailsHeader>
  <DetailsSections :sections="execution.sections" />
</template>
