<script setup lang="ts">
import TestExecution from "../common/TestExecution.ts";
import TestResultStatusIcon from "../common/TestResultStatusIcon.vue";
import TreeNode from "./TreeNode.vue";
import Selection from "../common/Selection.ts";
/* global TestNodeData */

const selection = defineModel<Selection | undefined>("selection");
defineProps<{ execution: TestExecution; roots: TestNodeData[] }>();
</script>

<template>
  <ul v-if="roots.length" class="pl-3">
    <li v-for="node in roots" :key="node.id">
      <TreeNode
        v-model:selection="selection"
        :execution="execution"
        :node="node"
        :children="execution.children(node as TestNodeData)"
      >
        <template #icon="iconProps">
          <TestResultStatusIcon
            :status="(node as TestNodeData).status"
            v-bind="iconProps"
          />
        </template>
      </TreeNode>
    </li>
  </ul>
</template>
