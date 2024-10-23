<script setup lang="ts">
import { ref } from 'vue'

defineProps<{ initialCount: Number }>()

const count = ref(initialCount)
</script>

<template>
  <h1>Hello World!</h1>
  <div>
    <button type="button" @click="count++">counter is at {{ count }}</button>
  </div>
</template>

<style scoped>
</style>
