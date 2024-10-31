<script setup lang="ts">
import { MoonStar, Sun } from 'lucide-vue-next';
import { useDark, useToggle } from '@vueuse/core'

const toggleDark = useToggle(useDark())
</script>

<template>
  <div class="cursor-pointer self-center hover:bg-black/20 rounded-full p-1" @click="toggleDark()">
    <Sun :size="16" class="dark:hidden" />
    <MoonStar :size="16" class="hidden dark:block" />
  </div>
</template>