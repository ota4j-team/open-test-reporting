<script setup lang="ts">
import TestExecution from '../TestExecution';
import TestResultStatusIcon from './TestResultStatusIcon.vue';
import TreeNode from './TreeNode.vue';
import Selection from '../Selection';

const selection = defineModel<Selection | undefined>('selection')
defineProps<{ execution: TestExecution, roots: TestNodeData[] }>()
</script>

<template>
  <ul v-if="roots.length" class="pl-3">
    <li v-for="node in roots" :key="node.id">
      <TreeNode :execution="execution" :node="node" :children="execution.children(node as TestNodeData)"
                v-model:selection="selection">
        <template #icon="iconProps">
          <TestResultStatusIcon :status="(node as TestNodeData).status" v-bind="iconProps" />
        </template>
      </TreeNode>
    </li>
  </ul>
</template>
