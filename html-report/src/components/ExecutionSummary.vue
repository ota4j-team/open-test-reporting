<script setup lang="ts">

defineProps<{ testCount: number }>()
</script>

<template>
  <h1>{{ $t('executionSummary.testCount', { count: testCount }, testCount) }}.</h1>
</template>

<style scoped>
</style>
