<script setup lang="ts">
import CodeBlock from './CodeBlock.vue';
import Section from './Section.vue'
defineProps<{ block: BlockData, depth: number }>()
</script>

<template>
  <p v-if="block.type === 'p'">{{ block.content }}</p>
  <table v-else-if="block.type === 'kvp'" class="w-full">
    <tbody>
      <tr v-for="(pair, index) in Object.entries(block.content)" :class="{
        'border-b': index < Object.entries(block.content).length - 1
      }" class="border-neutral-200 dark:border-neutral-700 w-full">
        <td class="w-44 pr-4 text-sm">{{ pair[0] }}</td>
        <td class="break-all">
          <a v-if="(pair[1] as string).startsWith('link:')" :href="(pair[1] as string).substring(5)" class="text-blue-600 dark:text-blue-500 hover:underline">
            <code class="text-sm">{{ (pair[1] as string).substring(5) }}</code>
          </a>
          <code v-else class="text-sm">{{ pair[1] }}</code>
        </td>
      </tr>
    </tbody>
  </table>
  <template v-else-if="block.type === 'sub'" v-for="section in (block.content as SectionData[])">
    <Section :section="section" :depth="depth + 1" />
  </template>
  <ul v-else-if="block.type == 'labels'" class="text-sm -mb-1">
    <li v-for="label in (block.content as string[])"
      class="inline-block rounded bg-neutral-200 dark:bg-neutral-700 mr-2 mb-1 px-2 py-0.5">{{ label }}</li>
  </ul>
  <CodeBlock v-else :content="block.content as string" />
</template>
