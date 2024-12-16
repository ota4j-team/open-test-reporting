<script setup lang="ts">
defineProps<{ title: string }>();
</script>

<template>
  <div
    class="z-10 sticky top-0 left-0 overflow-x-auto bg-neutral-100/50 dark:bg-neutral-800/50 backdrop-blur px-4 py-2.5"
  >
    <slot name="above" />
    <h2 class="text-2xl font-bold" role="heading" :aria-label="title">
      {{ title }}
    </h2>
    <slot name="below" />
  </div>
</template>
