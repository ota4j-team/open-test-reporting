import TestExecution from "./TestExecution.ts";

export default class Selection {
  public readonly execution: TestExecution;
  public readonly item: TestNodeData | TestExecution;

  constructor(execution: TestExecution, item: TestNodeData | TestExecution) {
    this.execution = execution;
    this.item = item;
  }
}
