<script setup lang="ts">
import ExecutionIcon from './ExecutionIcon.vue';
import TestExecution from '../TestExecution';
import TreeNode from './TreeNode.vue';
import Selection from '../Selection';

const selection = defineModel<Selection | undefined>('selection')
defineProps<{ executions: TestExecution[] }>()
</script>

<template>
  <ul v-if="executions.length">
    <li v-for="root in executions" :key="root.id">
      <TreeNode :execution="root" :node="root" :children="root.roots()"
                v-model:selection="selection">
        <template #icon="iconProps">
          <ExecutionIcon v-bind="iconProps" />
        </template>
      </TreeNode>
    </li>
  </ul>
</template>
