<script setup lang="ts">
import { computed } from 'vue';
import { ChevronDown, ChevronRight } from 'lucide-vue-next';
import TestNodeTree from './TestNodeTree.vue';
import TestExecution from '../TestExecution';
import Selection from '../Selection';
import { rootStore } from '../main';

const selection = defineModel<Selection | undefined>('selection')
const props = defineProps<{ execution: TestExecution, node: TestExecution | TestNodeData, children: TestNodeData[] }>()
function selectNodeAndShowChildren() {
  selection.value = new Selection(props.execution, props.node)
  const record = rootStore.nodes[props.node.id];
  if (record) {
    record.collapsed = false
  }
}
const isSelected = computed(() => selection.value?.item !== undefined && selection.value.item.id === props.node.id)
const toggleSize = 16
</script>

<template>
  <div class="inline-flex -mt-0.5" v-if="rootStore.isVisible(execution.nodeStatuses((node as TestNodeData)))">
    <div @click="rootStore.toggleNode(node.id)" class="cursor-pointer self-center" v-if="children.length > 0">
      <ChevronRight :size="toggleSize" v-if="rootStore.nodes[node.id]?.collapsed" />
      <ChevronDown :size="toggleSize" v-else />
    </div>
    <div class="cursor-pointer rounded p-px px-1 inline-flex" :class="{
      'bg-neutral-300 dark:bg-neutral-600 font-bold': isSelected,
      'hover:bg-neutral-200 dark:hover:bg-neutral-700': !isSelected,
      [`ml-[${toggleSize}px]`]: children.length == 0
    }" @click="selectNodeAndShowChildren()" role="link" :aria-label="node.name">
      <slot name="icon" :size="16" :strokeWidth="2.5" />
      <span class="ml-1 whitespace-nowrap">{{ node.name }}</span>
    </div>
  </div>
  <TestNodeTree v-if="children && !rootStore.nodes[node.id]?.collapsed" :execution="execution" :roots="children" v-model:selection="selection" />
</template>
