<script setup lang="ts">
import ExecutionIcon from './ExecutionIcon.vue';
import TestExecution from '../TestExecution';
import Tree from './Tree.vue';
import TreeNode from './TreeNode.vue';
import Selection from '../Selection';

const selection = defineModel<Selection | undefined>('selection')
defineProps<{ executions: TestExecution[] }>()
</script>

<template>
  <Tree :roots="executions">
    <template #default="{ node }">
      <TreeNode :execution="(node as TestExecution)" :node="node" :children="(node as TestExecution).roots()"
        v-model:selection="selection">
        <template #icon="iconProps">
          <ExecutionIcon v-bind="iconProps" />
        </template>
      </TreeNode>
    </template>
  </Tree>
</template>
