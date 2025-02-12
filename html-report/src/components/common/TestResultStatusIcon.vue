<script setup lang="ts">
import {
  CircleCheck,
  CircleX,
  SquareArrowOutUpRight,
  SquareSlash,
} from "lucide-vue-next";
import { testResultStatusForegroundColorClass } from "./TestResultStatus.ts";
import { defaultIconProps } from "./icon.ts";

const props = withDefaults(
  defineProps<{
    status: string;
    color?: string; // eslint-disable-line vue/require-default-prop
    size?: number; // eslint-disable-line vue/require-default-prop
    strokeWidth?: number; // eslint-disable-line vue/require-default-prop
  }>(),
  defaultIconProps,
);

function commonClasses(status: string) {
  return [
    "self-center",
    props.color ? props.color : testResultStatusForegroundColorClass(status),
  ];
}
</script>

<template>
  <CircleCheck
    v-if="status === 'SUCCESSFUL'"
    :size="size"
    :stroke-width="strokeWidth"
    :class="commonClasses(status)"
  />
  <CircleX
    v-else-if="status === 'FAILED' || status === 'ERRORED'"
    :size="size"
    :stroke-width="strokeWidth"
    :class="commonClasses(status)"
  />
  <SquareSlash
    v-else-if="status == 'SKIPPED'"
    :size="size"
    :stroke-width="strokeWidth"
    :class="commonClasses(status)"
  />
  <SquareArrowOutUpRight
    v-else
    :size="size"
    :stroke-width="strokeWidth"
    :class="commonClasses('ABORTED')"
  />
</template>
