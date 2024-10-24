<script setup lang="ts">
import { ref } from 'vue';

defineProps<{ node: TestNode }>()
const showChildren = ref(true)
function toggleChildren() {
  showChildren.value = !showChildren.value
}
</script>

<template>
  <span @click="toggleChildren">{{ node.name }}</span>
  <ul v-if="node.child && showChildren" class="list-disc list-inside pl-4">
    <li v-for="c in node.child" class="pt-1">
      <TestNode :node="c"/>
    </li>
  </ul>
</template>

<style scoped>
</style>
