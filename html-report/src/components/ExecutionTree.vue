<script setup lang="ts">
import { SquareActivity } from 'lucide-vue-next';
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
          <SquareActivity v-bind="iconProps" class="text-neutral-600 dark:text-neutral-300 self-center" />
        </template>
      </TreeNode>
    </template>
  </Tree>
</template>
