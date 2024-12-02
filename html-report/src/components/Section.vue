<script setup lang="ts">
import Block from './Block.vue'

defineEmits<{ imageClicked: [value: string] }>()
const { depth = 3 } = defineProps<{ section: SectionData, depth?: number }>()
</script>

<template>
  <h3 v-if="depth === 3" class="text-lg font-bold mt-3 mb-1">{{ section.title }}</h3>
  <h4 v-else-if="depth === 4" class="italic mt-2 mb-1">{{ section.title }}</h4>
  <h5 v-else>{{ section.title }}</h5>
  <template v-for="block in section.blocks">
    <Block :block="block" :depth="depth" @imageClicked="p => $emit('imageClicked', p)"/>
  </template>
</template>
