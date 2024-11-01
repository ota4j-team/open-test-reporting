<script setup lang="ts">
import { Check, Copy } from 'lucide-vue-next';
import { useClipboard } from '@vueuse/core'
import { computed } from 'vue';

const props = defineProps<{ content: string }>()
const source = computed(() => props.content)
const { copy, copied, isSupported } = useClipboard({ source })
</script>

<template>
  <div class="flex flex-row rounded-lg bg-neutral-50 dark:bg-neutral-900 border-neutral-200 dark:border-neutral-700 border p-1">
    <pre class="grow py-2 pl-2 text-sm h-1/2 max-h-96 w-full overflow-scroll" style="tab-size: 2"><code>{{ content }}</code></pre>
    <div v-if="isSupported">
      <div class="cursor-pointer hover:bg-black/20 dark:hover:bg-white/20 rounded-lg p-1" :title="$t('clipboard.copy')" @click="copy(source)">
        <Copy v-if="!copied" :size="16" />
        <Check v-else :size="16" />
      </div>
    </div>
  </div>
</template>
