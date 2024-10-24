<script setup lang="ts">

defineProps<{ testCount: number }>()
</script>

<template>
  <span>
    {{ $t('executionSummary.testCount', { count: testCount }, testCount) }}.
  </span>
</template>

<style scoped>
</style>
