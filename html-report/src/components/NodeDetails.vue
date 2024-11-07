<script setup lang="ts">
import { computed } from 'vue';
import TestResultStatusIcon from './TestResultStatusIcon.vue';
import { ChevronRight } from 'lucide-vue-next';
import TestExecution from '../TestExecution';
import testResultStatusColor from '../TestResultStatus';
import DurationLabel from './DurationLabel.vue';
import DetailsSections from './DetailsSections.vue';
import DetailsHeader from './DetailsHeader.vue';
import Selection from '../Selection';
import ExecutionIcon from './ExecutionIcon.vue';

const selection = defineModel<Selection | undefined>('selection')
const props = defineProps<{ node: TestNode, execution: TestExecution }>()
const color = computed(() => testResultStatusColor(props.node.status))
function selectNode(node: TestNode | TestExecution) {
  selection.value = new Selection(props.execution, node)
}
const parents = computed(() => props.execution.parents(props.node))
</script>

<template>
  <DetailsHeader :title="node.name">
    <template #above>
      <ul class="text-sm mb-3 inline-flex h-5">
        <li v-for="parent in parents" class="inline-flex">
          <ExecutionIcon v-if="(parent instanceof TestExecution)" class="-ml-px cursor-pointer"
            @click="selectNode(parent)" />
          <span v-else
            class="underline underline-offset-4 decoration-neutral-300 dark:decoration-neutral-700 hover:decoration-neutral-400 decoration-2 whitespace-nowrap cursor-pointer"
            @click="selectNode(parent)">
            {{ parent.name }}
          </span>
          <ChevronRight :size="16" class="inline self-center mx-1 text-neutral-500" />
        </li>
      </ul>
    </template>
    <template #below>
      <div class="mt-3">
        <div class="inline-flex mb-2 border-2 rounded-full px-2 py-1 mr-2"
          :class="[`border-${color}-600`, `bg-${color}-500`, `dark:border-${color}-500`, `dark:bg-${color}-600`]">
          <TestResultStatusIcon :status="node.status" :color="'white'" />
          <span class="ml-1 tracking-wide text-sm text-white font-bold self-center" role="status"
            :aria-label="node.status">{{ node.status }}</span>
        </div>
        <DurationLabel :millis="node.durationMillis" />
      </div>
    </template>
  </DetailsHeader>
  <DetailsSections :sections="node.sections" />
</template>
