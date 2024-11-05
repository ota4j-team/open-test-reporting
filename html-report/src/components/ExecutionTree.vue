<script setup lang="ts">
import { SquareActivity } from 'lucide-vue-next';
import TestExecution from '../TestExecution';
import Tree from './Tree.vue';
import TreeNode from './TreeNode.vue';

const selectedNode = defineModel<TestExecution | TestNode | undefined>('selectedNode')
defineProps<{ executions: TestExecution[] }>()
</script>

<template>
  <Tree :roots="executions">
    <template #default="{ node }">
      <TreeNode :execution="(node as TestExecution)" :node="node" :children="(node as TestExecution).roots()" v-model:selectedNode="selectedNode">
        <template #icon="iconProps">
          <SquareActivity v-bind="iconProps" class="text-neutral-600 dark:text-neutral-300" />
        </template>
      </TreeNode>
    </template>
  </Tree>
</template>
