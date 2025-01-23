<script setup lang="ts">
import { Check, Copy } from "lucide-vue-next";
import { useClipboard, useScroll } from "@vueuse/core";
import { computed, ref } from "vue";
import { defaultIconProps } from "../common/icon.ts";

const props = defineProps<{ content: string }>();

const source = computed(() => props.content);
const { copy, copied, isSupported } = useClipboard({ source });

const el = ref<HTMLElement | undefined>();
const { isScrolling } = useScroll(el, { idle: 2000 });
</script>

<template>
  <div
    class="z-0 relative rounded-sm bg-neutral-50 dark:bg-neutral-900 border-neutral-200 dark:border-neutral-700 border mb-2"
  >
    <pre
      ref="el"
      class="resize-y p-3 text-sm w-full overflow-auto min-h-10 max-h-96"
    ><code>{{ content }}</code></pre>
    <div
      v-if="isSupported"
      class="absolute bottom-2 right-2"
      :class="{ hidden: isScrolling }"
    >
      <div
        class="cursor-pointer bg-neutral-50 dark:bg-neutral-900 hover:bg-neutral-200 dark:hover:bg-neutral-700 border-neutral-200 dark:border-neutral-700 border rounded-lg p-1.5"
        :title="$t('clipboard.copy')"
        @click="copy(source)"
      >
        <Copy v-if="!copied" v-bind="defaultIconProps" />
        <Check v-else v-bind="defaultIconProps" />
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
