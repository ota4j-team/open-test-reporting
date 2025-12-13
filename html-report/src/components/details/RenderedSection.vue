<script setup lang="ts">
import RenderedBlock from "./RenderedBlock.vue";
/* global SectionData */

const { depth = 3 } = defineProps<{ section: SectionData; depth?: number }>();
</script>

<template>
  <div
    class="flex flex-row items-baseline mb-1"
    :class="{
      'mt-4': depth === 3,
      'mt-3': depth === 4,
      'mt-2': depth > 4,
    }"
  >
    <div class="flex-auto">
      <h3 v-if="depth === 3" class="text-lg font-bold">
        {{ section.title }}
      </h3>
      <h4 v-else-if="depth === 4" class="italic">
        {{ section.title }}
      </h4>
      <h5 v-else>
        {{ section.title }}
      </h5>
    </div>
    <span
      v-if="section.metaInfo"
      class="text-sm align-baseline text-neutral-500 dark:text-neutral-400"
    >
      {{ section.metaInfo }}
    </span>
  </div>
  <template v-for="(block, index) in section.blocks" :key="index">
    <RenderedBlock :block="block" :depth="depth" />
  </template>
</template>
