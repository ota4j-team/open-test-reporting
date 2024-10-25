<script setup lang="ts">
import { computed, ref } from 'vue';
import {  ChevronDown, ChevronRight } from 'lucide-vue-next';
import TestResultStatusIcon from './TestResultStatusIcon.vue';
import TestTree from './TestTree.vue';

const selectedNode = defineModel('selectedNode')
const props = defineProps<{ node: TestNode }>()
const showChildren = ref(true)
function toggleChildren() {
  showChildren.value = !showChildren.value
}
function selectNodeAndShowChildren() {
  selectedNode.value = props.node
  showChildren.value = true
}
const isSelected = computed(() => selectedNode.value !== undefined && (selectedNode.value as TestNode).name == props.node.name)
</script>

<template>
  <div class="inline-flex">
    <div @click="toggleChildren" class="cursor-pointer self-center" :class="{ 'invisible': !node.child }">
      <ChevronDown :size="16" v-if="showChildren" />
      <ChevronRight :size="16" v-else />
    </div>
    <div class="cursor-pointer rounded p-px px-1 inline-flex" :class="{ 'bg-gray-200 font-bold': isSelected }" @click="selectNodeAndShowChildren()">
      <TestResultStatusIcon :status="node.result.status" />
      <span class="ml-1">{{ node.name }}</span>
    </div>
  </div>
  <TestTree :roots="node.child" v-model:selectedNode="selectedNode" v-if="node.child" :class="{ hidden: !showChildren }"/>
</template>
