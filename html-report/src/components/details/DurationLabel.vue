<script setup lang="ts">
import { computed } from "vue";
import { Timer } from "lucide-vue-next";
import { defaultIconProps } from "../common/icon.ts";

const props = defineProps<{ millis: number }>();
const parts = computed(() => {
  const result = Object.fromEntries(
    Object.entries({
      hours: Math.floor(props.millis / 1000 / 60 / 60),
      minutes: Math.floor((props.millis / 1000 / 60) % 60),
      seconds: Math.floor((props.millis / 1000) % 60),
      millis: Math.floor(props.millis % 1000),
    }).filter(([, value]) => value > 0),
  );
  if (Object.keys(result).length === 0) {
    result.millis = 0;
  }
  return result;
});
</script>

<template>
  <div
    class="inline-flex mb-2 border-2 rounded-full px-2 py-1 border-neutral-400 bg-neutral-300 dark:bg-neutral-500"
  >
    <Timer v-bind="defaultIconProps" class="self-center" />
    <span class="ml-1 text-sm font-bold self-center">
      <span
        v-for="(value, label, index) in parts"
        v-bind:key="label"
        :class="{ 'mr-1': index < Object.entries(parts).length - 1 }"
        style="word-spacing: -0.1em"
        >{{ $t(`duration.${label}`, { count: value }, value) }}</span
      >
    </span>
  </div>
</template>
