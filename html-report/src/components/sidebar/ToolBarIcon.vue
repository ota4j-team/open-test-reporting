<script setup lang="ts">
defineProps<{ selected?: boolean }>();
</script>

<template>
  <div
    class="cursor-pointer self-center rounded p-1 mx-0.5"
    :class="{
      'bg-neutral-300 dark:bg-neutral-600': selected,
      'hover:bg-neutral-200 dark:hover:bg-neutral-700': !selected,
    }"
  >
    <slot />
  </div>
</template>
