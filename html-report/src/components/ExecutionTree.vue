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
          <SquareActivity v-bind="iconProps" />
        </template>
      </TreeNode>
    </template>
  </Tree>
</template>
