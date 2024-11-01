<script setup lang="ts">
import { computed, ref } from 'vue';
import {  ChevronDown, ChevronRight } from 'lucide-vue-next';
import TestResultStatusIcon from './TestResultStatusIcon.vue';
import TestTree from './TestTree.vue';
import TestNodeTree from '../TestNodeTree';

const selectedNode = defineModel('selectedNode')
const props = defineProps<{ tree: TestNodeTree, node: TestNode }>()
const showChildren = ref(true)
function toggleChildren() {
  showChildren.value = !showChildren.value
}
function selectNodeAndShowChildren() {
  selectedNode.value = props.node
  showChildren.value = true
}
const isSelected = computed(() => selectedNode.value !== undefined && (selectedNode.value as TestNode).id == props.node.id)
const children = computed(() => props.tree ? props.tree.children(props.node) : [])
const toggleSize = 16
</script>

<template>
  <div class="inline-flex">
    <div @click="toggleChildren" class="cursor-pointer self-center" v-if="children.length > 0">
      <ChevronDown :size="toggleSize" v-if="showChildren" />
      <ChevronRight :size="toggleSize" v-else />
    </div>
    <div
      class="cursor-pointer rounded p-px px-1 inline-flex"
      :class="{ 
        'bg-neutral-300 dark:bg-neutral-600 font-bold': isSelected, 
        'hover:bg-neutral-200 dark:hover:bg-neutral-700': !isSelected
      }"
      :style="children.length == 0 ? `margin-left: ${toggleSize}px`: ''"
      @click="selectNodeAndShowChildren()"
    >
      <TestResultStatusIcon :status="node.status" />
      <span class="ml-1">{{ node.name }}</span>
    </div>
  </div>
  <TestTree :tree="tree" :roots="children" v-model:selectedNode="selectedNode" v-if="children" :class="{ hidden: !showChildren }"/>
</template>
