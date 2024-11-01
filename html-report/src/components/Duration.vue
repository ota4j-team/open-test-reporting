<script setup lang="ts">
import { computed } from 'vue';

const props = defineProps<{ millis: number }>()
const parts = computed(() => {
  const result = Object.fromEntries(Object.entries({
    hours: Math.floor(props.millis / 1000 / 60 / 60),
    minutes: Math.floor(props.millis / 1000 / 60 % 60),
    seconds: Math.floor(props.millis / 1000 % 60),
    millis: Math.floor(props.millis % 1000),
  }).filter(([, value]) => value > 0))
  if (Object.keys(result).length === 0) {
    result.millis = 0
  }
  return result
})
</script>

<template>
  <span 
    v-for="(value, label, index) in parts"
    :class="{'mr-1': index < Object.entries(parts).length - 1}"
    style="word-spacing: -.2em"
  >{{ $t(`duration.${label}`, {count: value}, value) }}</span>
</template>
