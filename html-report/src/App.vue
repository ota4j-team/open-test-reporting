<script setup lang="ts">
import HelloWorld from './components/HelloWorld.vue'

defineProps<{ initialCount: Number }>()
</script>

<template>
  <HelloWorld :initialCount="initialCount" />
</template>

<style scoped>
</style>
