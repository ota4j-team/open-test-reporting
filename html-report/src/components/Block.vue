<script setup lang="ts">
import Section from './Section.vue'
defineProps<{ block: Block, depth: number }>()
</script>

<template>
  <p v-if="block.type === 'p'">{{ block.content }}</p>
  <table v-else-if="block.type === 'kvp'" class="w-full">
    <tbody>
      <tr v-for="(pair, index) in Object.entries(block.content)" :class="{'border-b': index < Object.entries(block.content).length - 1}" class="border-neutral-200 dark:border-neutral-700">
        <td class="pr-4 text-sm w-1/5">{{ pair[0] }}</td>
        <td class="w-4/5"><code class="text-sm">{{ pair[1] }}</code></td>
      </tr>
    </tbody>
  </table>
  <div v-else-if="block.type === 'sub'" v-for="section in (block.content as Section[])">
    <Section :section="section" :depth="depth + 1" />
  </div>
  <ul v-else-if="block.type == 'labels'" class="text-sm -mb-1">
    <li v-for="label in (block.content as string[])" class="inline-block rounded bg-neutral-200 dark:bg-neutral-700 mr-2 mb-1 px-2 py-0.5">{{ label }}</li>
  </ul>
  <pre v-else class="text-xs rounded-lg shadow-inner p-4 h-1/2 max-h-96 border w-full overflow-scroll">{{ block.content }}</pre>
</template>
