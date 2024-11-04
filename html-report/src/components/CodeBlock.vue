<script setup lang="ts">
import { Check, Copy } from 'lucide-vue-next';
import { useClipboard, useScroll } from '@vueuse/core'
import { computed, ref } from 'vue';

const props = defineProps<{ content: string }>()

const source = computed(() => props.content)
const { copy, copied, isSupported } = useClipboard({ source })

const el = ref<HTMLElement | undefined>()
const { isScrolling } = useScroll(el, { idle: 2000 })
</script>

<template>
  <div class="relative rounded bg-neutral-50 dark:bg-neutral-900 border-neutral-200 dark:border-neutral-700 border">
    <pre class="resize-y p-3 text-sm w-full overflow-auto min-h-14 max-h-96" ref="el"><code>{{ content }}</code></pre>
    <div class="absolute top-2 right-2" v-if="isSupported" :class="{'hidden': isScrolling}">
      <div class="cursor-pointer bg-neutral-50 dark:bg-neutral-900 hover:bg-neutral-200 dark:hover:bg-neutral-700 border-neutral-200 dark:border-neutral-700 border rounded-lg p-1.5" :title="$t('clipboard.copy')" @click="copy(source)">
        <Copy v-if="!copied" :size="16" />
        <Check v-else :size="16" />
      </div>
    </div>
  </div>
</template>

<style lang="css" scoped>
pre {
  tab-size: 2;
}
/* Allow resizing beyond initial max height */
pre[style*="height"] {
  max-height: max-content; 
} 
</style>