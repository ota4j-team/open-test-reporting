<script setup lang="ts">
import { ChevronsDownUp, ChevronsUpDown } from 'lucide-vue-next'
import { rootStore } from '../main'
import Icon from './Icon.vue';
import TestResultStatusIcon from "./TestResultStatusIcon.vue";
</script>

<template>
  <div class="flex flex-row h-10 justify-end">
    <Icon @click="rootStore.toggleShowSuccessful()" :selected="rootStore.showSuccessful" :title="$t('toolbar.showSuccessful')">
      <TestResultStatusIcon status="SUCCESSFUL" :size="16"/>
    </Icon>
    <Icon @click="rootStore.toggleShowFailed()" :selected="rootStore.showFailed" :title="$t('toolbar.showFailed')">
      <TestResultStatusIcon status="FAILED" :size="16"/>
    </Icon>
    <Icon @click="rootStore.toggleShowSkipped()" :selected="rootStore.showSkipped" :title="$t('toolbar.showSkipped')">
      <TestResultStatusIcon status="SKIPPED" :size="16"/>
    </Icon>
    <Icon @click="rootStore.toggleShowAborted()" :selected="rootStore.showAborted" :title="$t('toolbar.showAborted')">
      <TestResultStatusIcon status="ABORTED" :size="16"/>
    </Icon>
    <Icon @click="rootStore.expandAll()" :title="$t('toolbar.expandAll')">
      <ChevronsUpDown :size="16"/>
    </Icon>
    <Icon @click="rootStore.collapseAll()" :title="$t('toolbar.collapseAll')">
      <ChevronsDownUp :size="16"/>
    </Icon>
  </div>
</template>
