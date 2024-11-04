<script setup lang="ts">
import TestExecution from '../TestExecution';
import TestResultStatusIcon from './TestResultStatusIcon.vue';
import TreeNode from './TreeNode.vue';
import Tree from './Tree.vue';

const selectedNode = defineModel<TestExecution | TestNode | undefined>('selectedNode')
defineProps<{ execution: TestExecution, roots: TestNode[] }>()
</script>

<template>
  <Tree :roots="roots">
    <template #default="{ node }">
      <TreeNode :execution="execution" :node="node" :children="execution.children(node as TestNode)" v-model:selectedNode="selectedNode">
        <template #icon="iconProps">
          <TestResultStatusIcon :status="(node as TestNode).status" v-bind="iconProps" />
        </template>
      </TreeNode>
    </template>
  </Tree>
</template>
