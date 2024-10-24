<script setup lang="ts">

defineProps<{ testCount: number }>()
</script>

<template>
  <h1 class="text-3xl font-bold mb-4">
    {{ $t('executionSummary.testCount', { count: testCount }, testCount) }}.
  </h1>
</template>

<style scoped>
</style>
