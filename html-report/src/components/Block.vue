<script setup lang="ts">
import Section from './Section.vue'
defineProps<{ block: Block, depth: number }>()
</script>

<template>
  <p v-if="block.type === 'p'" class="text-sm">{{ block.content }}</p>
  <table v-if="block.type === 'kvp'">
    <tbody>
      <tr v-for="(pair, index) in Object.entries(block.content)" :class="{'border-b': index < Object.entries(block.content).length - 1}">
        <td class="pr-4 text-sm">{{ pair[0] }}</td>
        <td><code class="text-sm">{{ pair[1] }}</code></td>
      </tr>
    </tbody>
  </table>
  <pre v-if="block.type === 'pre'" class="text-xs rounded-lg shadow-inner p-4 h-1/2 max-h-96 border w-full overflow-scroll">{{ block.content }}</pre>
  <div v-if="block.type === 'sub'" v-for="section in (block.content as Section[])">
    <Section :section="section" :depth="depth + 1" />
  </div>
</template>
