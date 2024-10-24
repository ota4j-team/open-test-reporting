<script setup lang="ts">
import { ref } from 'vue';
import TestTree from './TestTree.vue';

const selectedNode = defineModel('selectedNode')
const props = defineProps<{ node: TestNode }>()
const showChildren = ref(true)
function toggleChildren() {
  showChildren.value = !showChildren.value
}
function selectNode() {
  selectedNode.value = props.node
}
</script>

<template>
  <button class="rounded px-2 bg-gray-200 mr-1" @click="toggleChildren" v-if="node.child"><span v-if="showChildren">-</span><span v-else>+</span></button>
  <span class="cursor-pointer rounded p-px px-1" :class="{ 'bg-gray-300': selectedNode !== undefined && (selectedNode as TestNode).name == node.name }" @click="selectNode">{{ node.name }}</span>
  <TestTree :roots="node.child" v-model:selectedNode="selectedNode" v-if="node.child" :class="{ hidden: !showChildren }"/>
</template>

<style scoped>
</style>
