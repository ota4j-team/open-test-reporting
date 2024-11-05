import TestExecution from "./TestExecution";

export default class Selection {

    public readonly execution: TestExecution;
    public readonly item: TestNode | TestExecution;

    constructor(execution: TestExecution, item: TestNode | TestExecution) {
        this.execution = execution;
        this.item = item;
    }
}
