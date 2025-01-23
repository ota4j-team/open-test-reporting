<script setup lang="ts">
import CodeBlock from "./CodeBlock.vue";
import RenderedSection from "./RenderedSection.vue";
import { inject } from "vue";
import { imageHandler } from "./keys.ts";
/* global BlockData */

defineProps<{ block: BlockData; depth: number }>();

const showImage = inject(imageHandler, () => {
  console.error("No image handler provided");
});
</script>

<template>
  <p v-if="block.type === 'p'">
    {{ block.content }}
  </p>
  <table v-else-if="block.type === 'kvp'" class="w-full my-2">
    <tbody>
      <tr
        v-for="(pair, index) in Object.entries(block.content)"
        :key="index"
        :class="{
          'border-b': index < Object.entries(block.content).length - 1,
        }"
        class="border-neutral-200 dark:border-neutral-700 w-full"
      >
        <td class="w-44 pr-4 text-sm py-1">
          {{ pair[0] }}
        </td>
        <td>
          <a
            v-if="(pair[1] as string).startsWith('link:')"
            :href="(pair[1] as string).substring(5)"
            class="text-blue-600 dark:text-blue-500 hover:underline"
          >
            <pre class="text-sm whitespace-pre-wrap break-all">{{
              (pair[1] as string).substring(5)
            }}</pre>
          </a>
          <pre v-else class="text-sm whitespace-pre-wrap break-all">{{
            pair[1]
          }}</pre>
        </td>
      </tr>
    </tbody>
  </table>
  <template
    v-for="section in block.content as SectionData[]"
    v-else-if="block.type === 'sub'"
    :key="section"
  >
    <RenderedSection :section="section" :depth="depth + 1" />
  </template>
  <ul v-else-if="block.type == 'labels'" class="text-sm -mb-1">
    <li
      v-for="label in block.content as string[]"
      :key="label"
      class="inline-block rounded-sm bg-neutral-200 dark:bg-neutral-700 mr-2 mb-1 px-2 py-0.5"
    >
      {{ label }}
    </li>
  </ul>
  <div v-else-if="block.type == 'img'" class="my-2">
    <a
      :href="block.content as string"
      @click.prevent="showImage(block.content)"
    >
      <img
        class="border-2 border-neutral-200 dark:border-neutral-700 hover:border-neutral-300 dark:hover:border-neutral-600 rounded-lg max-h-96"
        :src="block.content as string"
        :alt="(block as ImageBlockData).altText as string"
      />
    </a>
  </div>
  <CodeBlock v-else :content="block.content as string" />
</template>
