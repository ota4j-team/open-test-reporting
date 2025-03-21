import TestExecution from "../common/TestExecution.ts";
import { InjectionKey } from "vue";

export const treeStateKey = Symbol() as InjectionKey<TreeState>;

export default class TreeState {
  public readonly nodes: Record<string, NodeState>;

  public showAborted = true;
  public showFailedAndErrored = true;
  public showSkipped = true;
  public showSuccessful = true;

  constructor(executions: TestExecution[]) {
    this.nodes = executions.reduce(
      (prev, execution) => {
        return {
          ...prev,
          [execution.id]: {
            collapsed: false,
          },
          ...execution.nodesWithChildren().reduce((prev, node) => {
            const statuses = execution.nodeStatuses(node);
            const initiallyCollapsed =
              execution.parents(node).length > 1 &&
              statuses.indexOf("FAILED") == -1 &&
              statuses.indexOf("ERRORED") == -1;
            return {
              ...prev,
              [node.id]: {
                collapsed: initiallyCollapsed,
              },
            };
          }, {}),
        };
      },
      {} as Record<string, NodeState>,
    );
  }

  toggleShowAborted() {
    this.showAborted = !this.showAborted;
  }

  toggleShowFailedAndErrored() {
    this.showFailedAndErrored = !this.showFailedAndErrored;
  }

  toggleShowSuccessful() {
    this.showSuccessful = !this.showSuccessful;
  }

  toggleShowSkipped() {
    this.showSkipped = !this.showSkipped;
  }

  collapseAll() {
    Object.keys(this.nodes).forEach((key) => {
      this.nodes[key].collapsed = true;
    });
  }

  expandAll() {
    Object.keys(this.nodes).forEach((key) => {
      this.nodes[key].collapsed = false;
    });
  }

  toggleNode(id: string) {
    this.nodes[id].collapsed = !this.nodes[id].collapsed;
  }

  isVisible(statuses: string[]): boolean {
    return (
      statuses.length == 0 ||
      statuses.filter((status) => {
        switch (status) {
          case "SUCCESSFUL":
            return this.showSuccessful;
          case "FAILED":
          case "ERRORED":
            return this.showFailedAndErrored;
          case "SKIPPED":
            return this.showSkipped;
          case "ABORTED":
            return this.showAborted;
        }
      }).length > 0
    );
  }
}

type NodeState = {
  collapsed: boolean;
};
