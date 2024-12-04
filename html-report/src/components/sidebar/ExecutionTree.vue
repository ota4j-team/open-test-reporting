<script setup lang="ts">
import ExecutionIcon from '../common/ExecutionIcon.vue';
import TestExecution from '../common/TestExecution.ts';
import TreeNode from './TreeNode.vue';
import Selection from '../common/Selection.ts';

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
