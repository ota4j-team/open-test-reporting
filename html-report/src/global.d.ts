export declare global {
    interface TestNode {
        id: string,
        name: string,
        status: string,
        duration: string,
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
        var roots: string[];
        var children: { [id: string]: string[] };
        var testNodes: TestNode[];
    }
}
