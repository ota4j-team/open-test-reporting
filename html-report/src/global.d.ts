export declare global {
    interface TestNode {
        id: string,
        name: string,
        status: string,
    }
    declare module globalThis {
        var roots: string[];
        var children: { [id: string]: string[] };
        var testNodes: TestNode[];
    }
}
