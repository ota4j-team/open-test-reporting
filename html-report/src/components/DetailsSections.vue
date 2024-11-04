<script setup lang="ts">
import Section from './Section.vue';

defineProps<{ sections: Section[] | undefined }>()
</script>

<template>
  <div class="px-4 pb-4">
    <Section :section="section" v-for="section in sections" v-if="sections" />
    <p class="mt-3" v-else>No additional information</p>
  </div>
</template>
