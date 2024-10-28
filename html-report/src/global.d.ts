export declare global {
    interface Section {
        type: string,
        title: string,
        content: any
    }
    interface TestNode {
        id: string,
        name: string,
        status: string,
        sections: Section[],
    }
    declare module globalThis {
        var roots: string[];
        var children: { [id: string]: string[] };
        var testNodes: TestNode[];
    }
}
