<script setup lang="ts">
import {ChevronsDownUp, ChevronsUpDown} from 'lucide-vue-next'
import {rootStore} from '../../main.ts'
import ToolBarIcon from './ToolBarIcon.vue';
import TestResultStatusIcon from "../common/TestResultStatusIcon.vue";
import {defaultIconProps} from "../common/icon.ts";
</script>

<template>
  <div class="sticky top-0 bg-neutral-100 dark:bg-neutral-800 text-neutral-600 dark:text-neutral-300 border-b border-neutral-200 dark:border-neutral-700 flex flex-row px-1.5 py-2">
    <div class="flex flex-row flex-1">
      <ToolBarIcon @click="rootStore.toggleShowSuccessful()" :selected="rootStore.showSuccessful" :title="$t('toolbar.showSuccessful')">
        <TestResultStatusIcon status="SUCCESSFUL" v-bind="defaultIconProps" color="''"/>
      </ToolBarIcon>
      <ToolBarIcon @click="rootStore.toggleShowFailed()" :selected="rootStore.showFailed" :title="$t('toolbar.showFailed')">
        <TestResultStatusIcon status="FAILED" v-bind="defaultIconProps" color="''"/>
      </ToolBarIcon>
      <ToolBarIcon @click="rootStore.toggleShowSkipped()" :selected="rootStore.showSkipped" :title="$t('toolbar.showSkipped')">
        <TestResultStatusIcon status="SKIPPED" v-bind="defaultIconProps" color="''"/>
      </ToolBarIcon>
      <ToolBarIcon @click="rootStore.toggleShowAborted()" :selected="rootStore.showAborted" :title="$t('toolbar.showAborted')">
        <TestResultStatusIcon status="ABORTED" v-bind="defaultIconProps" color="''"/>
      </ToolBarIcon>
    </div>
    <div class="flex flex-row">
      <ToolBarIcon @click="rootStore.expandAll()" :title="$t('toolbar.expandAll')">
        <ChevronsUpDown v-bind="defaultIconProps"/>
      </ToolBarIcon>
      <ToolBarIcon @click="rootStore.collapseAll()" :title="$t('toolbar.collapseAll')">
        <ChevronsDownUp v-bind="defaultIconProps"/>
      </ToolBarIcon>
    </div>
    </div>
</template>
