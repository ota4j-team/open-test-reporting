export declare global {
  interface Data {
    id: string;
    name: string;
    durationMillis: number;
    sections: SectionData[] | undefined;
  }

  interface ExecutionData extends Data {
    roots: string[];
    children: Record<string, ChildMetadata>;
    testNodes: TestNodeData[] | undefined;
  }

  interface TestNodeData extends Data {
    status: string;
  }

  interface ChildMetadata {
    ids: string[];
    childStatuses: string[];
  }

  interface SectionData {
    title: string;
    metaInfo: string;
    blocks: BlockData[];
  }

  interface BlockData {
    type: string;
    content: never;
  }

  interface ImageBlockData extends BlockData {
    altText: string;
  }

  interface VideoBlockData extends BlockData {
    mediaType: string;
  }

  declare namespace globalThis {
    var testExecutions: ExecutionData[]; // eslint-disable-line no-var
  }
}
