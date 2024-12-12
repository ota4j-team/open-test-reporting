<script setup lang="ts">
import { MoonStar, Sun } from "lucide-vue-next";
import { BasicColorSchema, useDark, useToggle } from "@vueuse/core";
import { defaultIconProps } from "../common/icon.ts";

const toggleDark = useToggle(
  useDark({
    onChanged(
      _dark: boolean,
      defaultHandler: (mode: BasicColorSchema) => void,
      mode: BasicColorSchema,
    ) {
      defaultHandler(mode);
      // Toggle color of scrollbars
      document.body.style.colorScheme = mode;
    },
  }),
);
</script>

<template>
  <div
    class="cursor-pointer self-center hover:bg-black/20 rounded-full p-1"
    role="switch"
    @click="toggleDark()"
  >
    <Sun v-bind="defaultIconProps" class="dark:hidden" />
    <MoonStar v-bind="defaultIconProps" class="hidden dark:block" />
  </div>
</template>
