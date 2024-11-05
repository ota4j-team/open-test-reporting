export declare global {
    interface Node {
        id: string,
        name: string,
        durationMillis: number,
        sections: Section[] | undefined,
    }
    interface Execution extends Node {
        roots: string[];
        children: { [id: string]: string[] };
        testNodes: TestNode[] | undefined;
    }
    interface TestNode extends Node {
        status: string,
    }
    interface Section {
        title: string,
        blocks: Block[]
    }
    interface Block {
        type: string,
        content: any
    }
    declare module globalThis {
        var testExecutions: Execution[];
    }
}
