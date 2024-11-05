export default class TestExecution {

  static STATUSES = [
    'SKIPPED',
    'ABORTED',
    'SUCCESSFUL',
    'FAILED',
  ];

  public readonly id: string;
  public readonly name: string;
  public readonly durationMillis: number;
  public readonly sections: Section[];

  private readonly rootIds: string[]
  private readonly childrenIds: Map<string, string[]>
  private readonly parentIds: Map<string, string>
  private readonly testNodes: Map<string, TestNode>

  constructor(execution: Execution) {
    this.id = execution.id
    this.name = execution.name
    this.durationMillis = execution.durationMillis
    this.sections = execution.sections || []
    this.rootIds = execution.roots
    this.childrenIds = new Map(Object.entries(execution.children))
    this.parentIds = new Map<string, string>()
    this.childrenIds.forEach((children, p) => {
      children.forEach(c => this.parentIds.set(c, p))
    });
    this.testNodes = new Map(execution.testNodes?.map(n => [n.id, n]))
  }

  size(): number {
    return this.testNodes.size
  }

  roots(): TestNode[] {
    return this.rootIds.map(id => this.testNodes.get(id)!!)
  }

  children(node: TestNode): TestNode[] {
    if (this.childrenIds.has(node.id)) {
      return this.childrenIds.get(node.id)!!
        .map(id => this.testNodes.get(id)!!)
    }
    return []
  }

  parents(node: TestNode): (TestNode | TestExecution)[] {
    if (this.parentIds.has(node.id)) {
      const parentId = this.parentIds.get(node.id)!!
      const parent = this.testNodes.get(parentId)!!
      return [...this.parents(parent), parent]
    }
    return [this]
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

  initialSelection(): TestExecution | TestNode | undefined {
    const failedNodes = Array.from(this.testNodes.values())
      .filter(n => n.status === 'FAILED')
    if (failedNodes.length > 0) {
      return failedNodes[0]
    }
    return this
  }
}