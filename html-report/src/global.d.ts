export declare global {
    interface Execution {
        roots: string[];
        children: { [id: string]: string[] };
        testNodes: TestNode[];
    }
    interface TestNode {
        id: string,
        name: string,
        status: string,
        durationMillis: number,
        sections: Section[],
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
