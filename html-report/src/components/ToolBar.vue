<script setup lang="ts">
import { ChevronsDownUp, ChevronsUpDown } from 'lucide-vue-next'
import { rootStore } from '../main'
import Icon from './Icon.vue';
</script>

<template>
  <div class="flex flex-row h-10 justify-end">
    <Icon @click="rootStore.expandAll()" :title="$t('toolbar.expandAll')">
      <ChevronsUpDown :size="16"/>
    </Icon>
    <Icon @click="rootStore.collapseAll()" :title="$t('toolbar.collapseAll')">
      <ChevronsDownUp :size="16"/>
    </Icon>
  </div>
</template>
