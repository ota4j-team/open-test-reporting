<script setup lang="ts">
import {ResizableConfig, vResizable} from 'vue-resizables'
import ExecutionTree from "./ExecutionTree.vue";
import ToolBar from "./ToolBar.vue";
import TestExecution from "../../TestExecution.ts";
import Selection from "../../Selection.ts";
import {provide, reactive} from "vue";
import RootUi from "./TreeState.ts";
import {rootStoreKey} from "./keys.ts";

const selection = defineModel<Selection | undefined>('selection')
const props = defineProps<{ executions: TestExecution[] }>()

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

provide(rootStoreKey, reactive(new RootUi(props.executions)))
</script>

<template>
  <div v-resizable="resizeConfig"
       class="resize-x bg-neutral-100 dark:bg-neutral-800 min-w-80 text-sm">
    <ToolBar :executions="executions"/>
    <ExecutionTree :executions="executions" v-model:selection="selection" class="ml-1.5 mt-2.5"/>
  </div>
</template>
