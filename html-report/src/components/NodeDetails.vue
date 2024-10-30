<script setup lang="ts">
import { computed } from 'vue';
import Section from './Section.vue';
import TestResultStatusIcon from './TestResultStatusIcon.vue';
import { ChevronRight, Timer } from 'lucide-vue-next';
import TestNodeTree from '../TestNodeTree';
import testResultStatusColor from '../TestResultStatus';

const selectedNode = defineModel('selectedNode')
const props = defineProps<{ node: TestNode, tree: TestNodeTree }>()
const color = computed(() => testResultStatusColor(props.node.status))
function selectNode(node: TestNode) {
  selectedNode.value = node
}
</script>

<template>
  <div class="flex flex-col">
    <div class="bg-gray-50 p-4">
      <ul class="text-sm mb-3" v-if="!tree.isRoot(node)">
        <li v-for="parent in tree.parents(node)" class="inline-flex">
          <span @click="selectNode(parent)" class="underline underline-offset-4 decoration-gray-300 hover:decoration-gray-400 decoration-2 cursor-pointer">{{ parent.name }}</span>
          <ChevronRight :size="16" class="inline self-center mx-1 text-gray-600" />
        </li>
      </ul>
      <h2 class="text-xl font-bold mb-3">{{ node.name }}</h2>
      <div>
        <div class="inline-flex mb-2 border-2 rounded-full px-2 py-1 mr-2" :class="[`border-${color}-600`, `bg-${color}-500`]">
          <TestResultStatusIcon :status="node.status" :color="'white'" />
          <span class="ml-1 tracking-wide text-sm text-white font-bold">{{ node.status }}</span>
        </div>
        <div class="inline-flex mb-2 border-2 rounded-full px-2 py-1 border-gray-400 bg-gray-300">
          <Timer :size="16" :strokeWidth="3" class="self-center" />
          <span class="ml-1 tracking-wide text-sm font-bold">{{ node.duration }}</span>
        </div>
      </div>
    </div>
    <div class="px-4 pb-4 grow">
      <div v-for="section in node.sections">
        <Section :section="section" />
      </div>
    </div>
  </div>
</template>
