<script setup lang="ts">
import { computed, ref } from 'vue';
import {  ChevronDown, ChevronRight } from 'lucide-vue-next';
import TestTree from './TestTree.vue';
import TestExecution from '../TestExecution';

const selectedNode = defineModel<TestExecution | TestNode | undefined>('selectedNode')
const props = defineProps<{ execution: TestExecution, node: TestExecution | TestNode, children: TestNode[] }>()
const showChildren = ref(true)
function toggleChildren() {
  showChildren.value = !showChildren.value
}
function selectNodeAndShowChildren() {
  selectedNode.value = props.node
  showChildren.value = true
}
const isSelected = computed(() => selectedNode.value !== undefined && selectedNode.value.id == props.node.id)
const toggleSize = 16
</script>

<template>
  <div class="inline-flex -mt-0.5">
    <div @click="toggleChildren" class="cursor-pointer self-center" v-if="children.length > 0">
      <ChevronDown :size="toggleSize" v-if="showChildren" />
      <ChevronRight :size="toggleSize" v-else />
    </div>
    <div
      class="cursor-pointer rounded p-px px-1 inline-flex"
      :class="{ 
        'bg-neutral-300 dark:bg-neutral-600 font-bold': isSelected, 
        'hover:bg-neutral-200 dark:hover:bg-neutral-700': !isSelected,
        [`ml-[${toggleSize}px]`]: children.length == 0
      }"
      @click="selectNodeAndShowChildren()"
    >
      <slot name="icon" :size="16" :strokeWidth="2.5" />
      <span class="ml-1">{{ node.name }}</span>
    </div>
  </div>
  <TestTree :execution="execution" :roots="children" v-model:selectedNode="selectedNode" v-if="children" :class="{ hidden: !showChildren }"/>
</template>
