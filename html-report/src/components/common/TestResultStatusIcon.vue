<script setup lang="ts">
import {CircleCheck, CircleX, SquareArrowOutUpRight, SquareSlash} from 'lucide-vue-next';
import testResultStatusColor from './TestResultStatus.ts';
import {defaultIconProps} from "./icon.ts";

const props = withDefaults(defineProps<{ status: string, color?: string, size?: number, strokeWidth?: number }>(), defaultIconProps)
function commonClasses(status: string) {
  return ['self-center', ...props.color ? [props.color] : [`text-${testResultStatusColor(status)}-600`]]
}
</script>

<template>
  <CircleCheck v-if="status === 'SUCCESSFUL'" :size="size" :strokeWidth="strokeWidth" :class="commonClasses('SUCCESSFUL')" />
  <CircleX v-else-if="status === 'FAILED'" :size="size" :strokeWidth="strokeWidth" :class="commonClasses('FAILED')" />
  <SquareSlash v-else-if="status == 'SKIPPED'" :size="size" :strokeWidth="strokeWidth" :class="commonClasses('SKIPPED')" />
  <SquareArrowOutUpRight v-else :size="size" :strokeWidth="strokeWidth" :class="commonClasses('ABORTED')"/>
</template>
