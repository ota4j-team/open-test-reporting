<script setup lang="ts">
import { CircleCheck, CircleX, SkipForward, SquareSlash } from 'lucide-vue-next';
import testResultStatusColor from '../TestResultStatus';

const props = defineProps<{ status: string, color?: string }>()
const size = 16
const strokeWidth = 3
function commonClasses(status: string) {
  return ['self-center', ...props.color ? [`text-${props.color}`] : [`text-${testResultStatusColor(status)}-600`]]
}
</script>

<template>
  <CircleCheck v-if="status === 'SUCCESSFUL'" :size="size" :strokeWidth="strokeWidth" :class="commonClasses('SUCCESSFUL')" />
  <CircleX v-else-if="status === 'FAILED'" :size="size" :strokeWidth="strokeWidth" :class="commonClasses('FAILED')" />
  <SkipForward v-else-if="status == 'SKIPPED'" :size="size" :strokeWidth="strokeWidth" :class="commonClasses('SKIPPED')" />
  <SquareSlash v-else :size="size" :strokeWidth="strokeWidth" :class="commonClasses('ABORTED')"/>
</template>
