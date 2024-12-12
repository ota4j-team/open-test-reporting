<script setup lang="ts">
import { ChevronsDownUp, ChevronsUpDown } from "lucide-vue-next";
import ToolBarIcon from "./ToolBarIcon.vue";
import TestResultStatusIcon from "../common/TestResultStatusIcon.vue";
import { defaultIconProps } from "../common/icon.ts";
import { inject } from "vue";
import { treeStateKey } from "./TreeState.ts";

const treeState = inject(treeStateKey)!;
</script>

<template>
  <div
    class="bg-neutral-100 dark:bg-neutral-800 text-neutral-600 dark:text-neutral-300 border-b border-neutral-200 dark:border-neutral-700 flex flex-row px-1.5 py-2"
  >
    <div class="flex flex-row flex-1">
      <ToolBarIcon
        @click="treeState.toggleShowSuccessful()"
        :selected="treeState.showSuccessful"
        :title="$t('toolbar.showSuccessful')"
      >
        <TestResultStatusIcon
          status="SUCCESSFUL"
          v-bind="defaultIconProps"
          color="''"
        />
      </ToolBarIcon>
      <ToolBarIcon
        @click="treeState.toggleShowFailed()"
        :selected="treeState.showFailed"
        :title="$t('toolbar.showFailed')"
      >
        <TestResultStatusIcon
          status="FAILED"
          v-bind="defaultIconProps"
          color="''"
        />
      </ToolBarIcon>
      <ToolBarIcon
        @click="treeState.toggleShowSkipped()"
        :selected="treeState.showSkipped"
        :title="$t('toolbar.showSkipped')"
      >
        <TestResultStatusIcon
          status="SKIPPED"
          v-bind="defaultIconProps"
          color="''"
        />
      </ToolBarIcon>
      <ToolBarIcon
        @click="treeState.toggleShowAborted()"
        :selected="treeState.showAborted"
        :title="$t('toolbar.showAborted')"
      >
        <TestResultStatusIcon
          status="ABORTED"
          v-bind="defaultIconProps"
          color="''"
        />
      </ToolBarIcon>
    </div>
    <div class="flex flex-row">
      <ToolBarIcon
        @click="treeState.expandAll()"
        :title="$t('toolbar.expandAll')"
      >
        <ChevronsUpDown v-bind="defaultIconProps" />
      </ToolBarIcon>
      <ToolBarIcon
        @click="treeState.collapseAll()"
        :title="$t('toolbar.collapseAll')"
      >
        <ChevronsDownUp v-bind="defaultIconProps" />
      </ToolBarIcon>
    </div>
  </div>
</template>
