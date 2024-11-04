<script setup lang="ts">
import { computed } from 'vue';
import TestResultStatusIcon from './TestResultStatusIcon.vue';
import { ChevronRight, Timer } from 'lucide-vue-next';
import TestExecution from '../TestExecution';
import testResultStatusColor from '../TestResultStatus';
import Duration from './Duration.vue';
import DetailsSections from './DetailsSections.vue';
import DetailsHeader from './DetailsHeader.vue';

const selectedNode = defineModel<TestNode | TestExecution | undefined>('selectedNode')
const props = defineProps<{ node: TestNode, execution: TestExecution }>()
const color = computed(() => testResultStatusColor(props.node.status))
function selectNode(node: TestNode | TestExecution) {
  selectedNode.value = node
}
const parents = computed(() => props.execution.parents(props.node))
</script>

<template>
  <DetailsHeader :title="node.name">
    <template #above>
      <ul class="text-sm mb-3" v-if="parents">
        <li v-for="parent in parents" class="inline-flex">
          <span @click="selectNode(parent)" class="underline underline-offset-4 decoration-neutral-300 dark:decoration-neutral-700 hover:decoration-neutral-400 decoration-2 cursor-pointer">{{ parent.name }}</span>
          <ChevronRight :size="16" class="inline self-center mx-1 text-neutral-500" />
        </li>
      </ul>
    </template>
    <template #below>
      <div class="mt-3">
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
    </template>
  </DetailsHeader>
  <DetailsSections :sections="node.sections" />
</template>
