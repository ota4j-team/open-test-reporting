export declare global {
    interface Data {
        id: string,
        name: string,
        durationMillis: number,
        sections: SectionData[] | undefined,
    }
    interface ExecutionData extends Data {
        roots: string[];
        children: { [id: string]: string[] };
        testNodes: TestNodeData[] | undefined;
    }
    interface TestNodeData extends Data {
        status: string,
    }
    interface SectionData {
        title: string,
        blocks: BlockData[]
    }
    interface BlockData {
        type: string,
        content: any
    }
    declare module globalThis {
        var testExecutions: ExecutionData[];
    }
}
