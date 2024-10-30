<script setup lang="ts">
import { computed } from 'vue';
import Section from './Section.vue';
import TestResultStatusIcon from './TestResultStatusIcon.vue';
import { ChevronRight, Timer } from 'lucide-vue-next';
import TestNodeTree from '../TestNodeTree';

const selectedNode = defineModel('selectedNode')
const props = defineProps<{ node: TestNode, tree: TestNodeTree }>()
const color = computed(() => props.node.status === 'SUCCESSFUL' ? 'green' : 'red')
function selectNode(node: TestNode) {
  selectedNode.value = node
}
</script>

<template>
  <ul class="text-sm mb-2">
    <li v-for="parent in tree.parents(node)" class="inline-flex">
      <span @click="selectNode(parent)" class="underline decoration-gray-300 decoration-2 cursor-pointer">{{ parent.name }}</span>
      <ChevronRight :size="16" class="inline self-center mx-1 text-gray-600" />
    </li>
  </ul>
  <h2 class="text-xl mb-4 font-bold">{{ node.name }}</h2>
  <div class="inline-flex mb-2 border-2 rounded-full px-2 py-1 mr-2" :class="[`border-${color}-600`, `bg-${color}-500`]">
    <TestResultStatusIcon :status="node.status" class="text-white" />
    <span class="ml-1 tracking-wide text-sm text-white font-bold">{{ node.status }}</span>
  </div>
  <div class="inline-flex mb-2 border-2 rounded-full px-2 py-1 border-gray-400 bg-gray-300">
    <Timer :size="16" :strokeWidth="3" class="self-center" />
    <span class="ml-1 tracking-wide text-sm font-bold">{{ node.duration }}</span>
  </div>
  <div v-for="section in node.sections">
    <Section :section="section" />
  </div>
</template>
