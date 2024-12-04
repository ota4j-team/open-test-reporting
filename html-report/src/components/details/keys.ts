import { InjectionKey } from "vue";

export const imageHandler = Symbol() as InjectionKey<(a: string) => void>;
