export declare global {
    interface TestNode {
        name: string,
        child: Array<TestNode>,
    }
    declare module globalThis {
        var data: {
            testCount: number,
            root: TestNode, // TODO Array<TestNode>
        };
    }
}
