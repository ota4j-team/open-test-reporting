<script setup lang="ts">
import TestExecution from '../TestExecution';

defineProps<{ roots: TestNode[] | TestExecution[] }>()
</script>

<template>
  <ul v-if="roots.length" class="pl-3">
    <li v-for="root in roots" :key="root.id">
      <slot :node="root" />
    </li>
  </ul>
</template>
