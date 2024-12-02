export declare global {
    interface Data {
        id: string,
        name: string,
        durationMillis: number,
        sections: SectionData[] | undefined,
    }
    interface ExecutionData extends Data {
        roots: string[];
        children: Record<string, ChildMetadata>;
        testNodes: TestNodeData[] | undefined;
    }
    interface TestNodeData extends Data {
        status: string,
    }
    interface ChildMetadata {
        ids: string[],
        childStatuses: string[]
    }
    interface SectionData {
        title: string,
        blocks: BlockData[]
    }
    interface BlockData {
        type: string,
        content: any
    }
    interface ImageBlockData extends BlockData {
        altText: string
    }
    declare module globalThis {
        var testExecutions: ExecutionData[];
    }
}
