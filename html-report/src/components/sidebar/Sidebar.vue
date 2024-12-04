<script setup lang="ts">
import {ResizableConfig, vResizable} from 'vue-resizables'
import ExecutionTree from "./ExecutionTree.vue";
import ToolBar from "./ToolBar.vue";
import TestExecution from "../common/TestExecution.ts";
import Selection from "../common/Selection.ts";
import {provide, reactive} from "vue";
import TreeState, {treeStateKey} from "./TreeState.ts";

const selection = defineModel<Selection | undefined>('selection')
const props = defineProps<{ executions: TestExecution[] }>()

provide(treeStateKey, reactive(new TreeState(props.executions)))

const resizeConfig: ResizableConfig = {
  edge: {
    right: true,
  },
  size: {
    min: {
      width: 320, // min-w-80
    }
  }
}
</script>

<template>
  <div v-resizable="resizeConfig"
       class="resize-x bg-neutral-100 dark:bg-neutral-800 min-w-80 text-sm">
    <ToolBar :executions="executions"/>
    <ExecutionTree :executions="executions" v-model:selection="selection" class="ml-1.5 mt-2.5"/>
  </div>
</template>
