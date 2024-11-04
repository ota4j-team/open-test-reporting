<script setup lang="ts">
defineProps<{ title: string }>()
</script>

<template>
  <div class="sticky top-0 bg-neutral-100/50 dark:bg-neutral-800/50 backdrop-blur p-4">
    <slot name="above" />
    <h2 class="text-xl font-bold">{{ title }}</h2>
    <slot name="below" />
  </div>
</template>
