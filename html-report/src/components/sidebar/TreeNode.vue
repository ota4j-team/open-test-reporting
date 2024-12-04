<script setup lang="ts">
import { computed, inject } from "vue";
import { ChevronDown, ChevronRight } from "lucide-vue-next";
import TestNodeTree from "./TestNodeTree.vue";
import TestExecution from "../common/TestExecution.ts";
import Selection from "../common/Selection.ts";
import { defaultIconProps } from "../common/icon.ts";
import { treeStateKey } from "./TreeState.ts";

const treeState = inject(treeStateKey)!!;
const selection = defineModel<Selection | undefined>("selection");
const props = defineProps<{
  execution: TestExecution;
  node: TestExecution | TestNodeData;
  children: TestNodeData[];
}>();
const isSelected = computed(
  () =>
    selection.value?.item !== undefined &&
    selection.value.item.id === props.node.id,
);
const showChildren = computed(
  () => props.children.length > 0 && !treeState.nodes[props.node.id]?.collapsed,
);

function selectAndExpandNode() {
  selection.value = new Selection(props.execution, props.node);
  const record = treeState.nodes[props.node.id];
  if (record) {
    record.collapsed = false;
  }
}
</script>

<template>
  <div
    class="inline-flex -mt-0.5"
    v-if="treeState.isVisible(execution.nodeStatuses(node as TestNodeData))"
  >
    <div
      @click="treeState.toggleNode(node.id)"
      class="cursor-pointer self-center"
      v-if="children.length > 0"
    >
      <ChevronRight
        v-bind="defaultIconProps"
        v-if="treeState.nodes[node.id]?.collapsed"
      />
      <ChevronDown v-bind="defaultIconProps" v-else />
    </div>
    <div
      class="cursor-pointer rounded p-px px-1 inline-flex"
      :class="{
        'bg-neutral-300 dark:bg-neutral-600 font-bold': isSelected,
        'hover:bg-neutral-200 dark:hover:bg-neutral-700': !isSelected,
        [`ml-[${defaultIconProps.size}px]`]: children.length == 0,
      }"
      @click="selectAndExpandNode()"
      role="link"
      :aria-label="node.name"
    >
      <slot name="icon" v-bind="defaultIconProps" />
      <span class="ml-1 whitespace-nowrap">{{ node.name }}</span>
    </div>
  </div>
  <TestNodeTree
    v-if="showChildren"
    :execution="execution"
    :roots="children"
    v-model:selection="selection"
  />
</template>
