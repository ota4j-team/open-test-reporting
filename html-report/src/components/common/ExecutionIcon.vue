<script setup lang="ts">
import { SquareActivity } from 'lucide-vue-next';

const props = withDefaults(defineProps<{ size?: number, strokeWidth?: number }>(), {
  size: 16,
  strokeWidth: 2.5,
})
</script>

<template>
  <SquareActivity v-bind="props" class="text-neutral-600 dark:text-neutral-300 self-center" />
</template>
