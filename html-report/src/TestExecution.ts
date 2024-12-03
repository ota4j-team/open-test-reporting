import Selection from "./Selection";

export default class TestExecution {

  private static readonly STATUSES = [
    'SKIPPED',
    'ABORTED',
    'SUCCESSFUL',
    'FAILED',
  ];

  static initialSelection(executions: TestExecution[]): Selection | undefined {
    const failedExecution = executions
      .find(e => e.overallStatus() === 'FAILED')
    if (failedExecution) {
      const failedNode = Array.from(failedExecution.testNodes.values())
        .find(n => n.status === 'FAILED')
      if (failedNode) {
        return new Selection(failedExecution, failedNode)
      }
    }
    if (executions.length > 0) {
      return new Selection(executions[0], executions[0])
    }
    return undefined
  }

  static overallStatus(executions: TestExecution[]): string {
    var statuses = executions
      .map(e => TestExecution.STATUSES.indexOf(e.overallStatus()))
      .sort()
    return statuses.length > 0
      ? TestExecution.STATUSES[statuses[statuses.length - 1]]
      : TestExecution.STATUSES[0]
  }

  static statusCount(executions: TestExecution[]): Map<string, number> {
    const statusCount = new Map<string, number>()
    TestExecution.STATUSES.forEach(s => statusCount.set(s, 0))
    executions.forEach(e => {
      e.statusCount().forEach((count, status) => {
        statusCount.set(status, statusCount.get(status)!! + count)
      })
    })
    return statusCount
  }

  public readonly id: string;
  public readonly name: string;
  public readonly durationMillis: number;
  public readonly sections: SectionData[];

  private readonly rootIds: string[]
  private readonly childrenMetadata: Map<string, ChildMetadata>
  private readonly parentIds: Map<string, string>
  private readonly testNodes: Map<string, TestNodeData>

  constructor(execution: ExecutionData) {
    this.id = execution.id
    this.name = execution.name
    this.durationMillis = execution.durationMillis
    this.sections = execution.sections || []
    this.rootIds = execution.roots || []
    this.childrenMetadata = new Map(Object.entries(execution.children ? execution.children : []))
    this.parentIds = new Map<string, string>()
    this.childrenMetadata.forEach((children, p) => {
      children.ids?.forEach(c => this.parentIds.set(c, p))
    });
    this.testNodes = new Map(execution.testNodes?.map(n => [n.id, n]))
  }

  size(): number {
    return this.testNodes.size
  }

  nodesWithChildren(): TestNodeData[] {
    return Array.from(this.childrenMetadata.keys())
        .map(id => this.testNodes.get(id)!!)
  }

  roots(): TestNodeData[] {
    return this.rootIds.map(id => this.testNodes.get(id)!!)
  }

  children(node: TestNodeData): TestNodeData[] {
    if (this.childrenMetadata.has(node.id)) {
      return this.childrenMetadata.get(node.id)!!
        .ids!!
        .map(id => this.testNodes.get(id)!!)
    }
    return []
  }

  parents(node: TestNodeData): (TestNodeData | TestExecution)[] {
    if (this.parentIds.has(node.id)) {
      const parentId = this.parentIds.get(node.id)!!
      const parent = this.testNodes.get(parentId)!!
      return [...this.parents(parent), parent]
    }
    return [this]
  }

  nodeStatuses(node: TestNodeData): string[] {
    if (this.childrenMetadata.has(node.id)) {
      return this.childrenMetadata.get(node.id)!!
        .childStatuses!!
    }
    return node.status ? [node.status] : []
  }

  overallStatus(): string {
    var statuses = Array.from(this.testNodes.values())
      .map(node => TestExecution.STATUSES.indexOf(node.status))
      .sort()
    return statuses.length > 0
      ? TestExecution.STATUSES[statuses[statuses.length - 1]]
      : TestExecution.STATUSES[0]
  }

  statusCount(): Map<string, number> {
    const result = new Map<string, number>()
    TestExecution.STATUSES.forEach(s => result.set(s, 0))
    Array.from(this.testNodes.values())
      .forEach(n => result.set(n.status, result.get(n.status)!! + 1))
    return result
  }
}
