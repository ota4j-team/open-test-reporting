<script setup lang="ts">
import Section from "./Section.vue";
import VueEasyLightbox from "vue-easy-lightbox";
import { computed, provide, ref } from "vue";
import { imageHandler } from "./keys.ts";

const props = defineProps<{ sections: SectionData[] | undefined }>();

const lightboxVisible = ref(false);
const lightboxIndex = ref(0);

interface Image {
  src: string;
  title: string;
  alt: string;
}

const images = computed<Image[]>(() => {
  if (props.sections) {
    return props.sections
      .flatMap((s) => s.blocks)
      .flatMap(allBlocks)
      .filter((b) => b.type === "img")
      .map((b) => b as ImageBlockData)
      .map((b) => ({
        src: b.content as string,
        title: b.altText,
        alt: b.altText,
      }));
  }
  return [];
});
provide(imageHandler, (path) => {
  lightboxIndex.value = images.value.map((img) => img.src).indexOf(path);
  lightboxVisible.value = true;
});

function allBlocks(block: BlockData): BlockData[] {
  return block.type === "sub"
    ? (block.content as SectionData[])
        .flatMap((s) => s.blocks)
        .flatMap(allBlocks)
    : [block];
}
</script>

<template>
  <div class="px-4 pb-4">
    <Section
      :section="section"
      v-for="section in sections"
      v-if="sections && sections.length > 0"
    />
    <p class="mt-3" v-else>No additional information</p>
  </div>
  <VueEasyLightbox
    v-if="images.length > 0"
    :visible="lightboxVisible"
    :imgs="images"
    :index="lightboxIndex"
    @hide="lightboxVisible = false"
  />
</template>
