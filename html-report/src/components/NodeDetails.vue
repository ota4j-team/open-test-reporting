<script setup lang="ts">
import { computed } from 'vue';
import Section from './Section.vue';
import TestResultStatusIcon from './TestResultStatusIcon.vue';
import { ChevronRight, Timer } from 'lucide-vue-next';
import TestExecution from '../TestExecution';
import testResultStatusColor from '../TestResultStatus';
import Duration from './Duration.vue';

const selectedNode = defineModel('selectedNode')
const props = defineProps<{ node: TestNode, execution: TestExecution }>()
const color = computed(() => testResultStatusColor(props.node.status))
function selectNode(node: TestNode) {
  selectedNode.value = node
}
</script>

<template>
  <div class="sticky top-0 bg-neutral-100/50 dark:bg-neutral-800/50 backdrop-blur p-4">
    <ul class="text-sm mb-3" v-if="!execution.isRoot(node)">
      <li v-for="parent in execution.parents(node)" class="inline-flex">
        <span @click="selectNode(parent)" class="underline underline-offset-4 decoration-neutral-300 dark:decoration-neutral-700 hover:decoration-neutral-400 decoration-2 cursor-pointer">{{ parent.name }}</span>
        <ChevronRight :size="16" class="inline self-center mx-1 text-neutral-500" />
      </li>
    </ul>
    <h2 class="text-xl font-bold mb-3">{{ node.name }}</h2>
    <div>
      <div class="inline-flex mb-2 border-2 rounded-full px-2 py-1 mr-2" :class="[`border-${color}-600`, `bg-${color}-500`, `dark:border-${color}-500`, `dark:bg-${color}-600`]">
        <TestResultStatusIcon :status="node.status" :color="'white'" />
        <span class="ml-1 tracking-wide text-sm text-white font-bold self-center">{{ node.status }}</span>
      </div>
      <div class="inline-flex mb-2 border-2 rounded-full px-2 py-1 border-neutral-400 bg-neutral-300 dark:bg-neutral-500">
        <Timer :size="16" :strokeWidth="3" class="self-center" />
        <span class="ml-1 text-sm font-bold self-center">
          <Duration :millis="node.durationMillis" />
        </span>
      </div>
    </div>
  </div>
  <div class="px-4 pb-4">
    <Section :section="section" v-for="section in node.sections" v-if="node.sections" />
    <p class="mt-3" v-else>No additional information</p>
  </div>
</template>
