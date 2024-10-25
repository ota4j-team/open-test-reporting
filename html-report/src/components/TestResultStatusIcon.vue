<script setup lang="ts">
import { CircleCheck, CircleX } from 'lucide-vue-next';

defineProps<{ status: string }>()
const size = 16
const strokeWidth = 3
const commonClasses = ['self-center']
</script>

<template>
  <CircleCheck v-if="status === 'SUCCESSFUL'" class="text-green-600" :size="size" :strokeWidth="strokeWidth" :class="commonClasses" />
  <CircleX v-else class="text-red-600" :size="size" :strokeWidth="strokeWidth" :class="commonClasses" />
</template>
