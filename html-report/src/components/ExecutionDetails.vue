<script setup lang="ts">
import TestExecution from '../TestExecution';
import DetailsHeader from './DetailsHeader.vue';
import DetailsSections from './DetailsSections.vue';

defineProps<{ execution: TestExecution }>()
</script>

<template>
  <DetailsHeader :title="execution.name" />
  <DetailsSections :sections="execution.sections" />
</template>
