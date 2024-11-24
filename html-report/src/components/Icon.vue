<script setup lang="ts">
defineProps<{ selected?: boolean }>()
</script>

<template>
  <div class="cursor-pointer self-center hover:bg-black/20 rounded-full p-1" :class="{
    'bg-black/50' : selected
  }">
    <slot></slot>
  </div>
</template>
