import TestExecution from "../../TestExecution.ts";

export default class RootUi {

    public readonly nodes: Record<string, NodeUi>

    public showAborted = true
    public showFailed = true
    public showSkipped = true
    public showSuccessful = true

    constructor(executions: TestExecution[]) {
        this.nodes = executions
          .reduce((prev, execution) => {
              return {
                  ...prev,
                  [execution.id]: {
                      collapsed: false,
                  },
                  ...execution.nodesWithChildren().reduce((prev, node) => {
                      const statuses = execution.nodeStatuses(node);
                      let initiallyCollapsed = execution.parents(node).length > 1 && statuses.indexOf('FAILED') == -1;
                      return {
                          ...prev,
                          [node.id]: {
                              collapsed: initiallyCollapsed,
                          }
                      }
                  }, {})
              }
          }, {} as Record<string, NodeUi>)
    }

    toggleShowAborted() {
        this.showAborted = !this.showAborted;
    }

    toggleShowFailed() {
        this.showFailed = !this.showFailed;
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
        return statuses.length == 0 || statuses.filter(status => {
            switch (status) {
                case 'SUCCESSFUL':
                    return this.showSuccessful;
                case 'FAILED':
                    return this.showFailed;
                case 'SKIPPED':
                    return this.showSkipped;
                case 'ABORTED':
                    return this.showAborted;
            }
        }).length > 0;
    }
}

type NodeUi = {
    collapsed: boolean
}
