<script setup lang="ts">
import { FileChartColumn } from "lucide-vue-next";
import { defaultIconProps } from "./icon.ts";

const props = withDefaults(
  // eslint-disable-next-line vue/require-default-prop
  defineProps<{ size?: number; strokeWidth?: number }>(),
  defaultIconProps,
);
</script>

<template>
  <FileChartColumn
    v-bind="props"
    class="text-neutral-600 dark:text-neutral-300 self-center"
  />
</template>
