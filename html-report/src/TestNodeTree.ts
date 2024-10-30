export default class TestNodeTree {

  private readonly rootIds: string[];
  private readonly childrenIds: Map<string, string[]>;
  private readonly parentIds: Map<string, string>;
  private readonly testNodes: Map<string, TestNode>;

  constructor(
    rootIds: string[],
    childrenIds: Map<string, string[]>,
    testNodes: TestNode[]
  ) {
    this.rootIds = rootIds
    this.childrenIds = childrenIds
    this.parentIds = new Map<string, string>()
    this.childrenIds.forEach((children, p) => {
      children.forEach(c => this.parentIds.set(c, p))
    });
    this.testNodes = new Map(testNodes.map(n => [n.id, n]))
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

  parents(node: TestNode): TestNode[] {
    if (this.parentIds.has(node.id)) {
      const parentId = this.parentIds.get(node.id)!!
      const parent = this.testNodes.get(parentId)!!
      return [...this.parents(parent), parent]
    }
    return []
  }

  isRoot(node: TestNode): boolean {
    return !this.parentIds.has(node.id)
  }
}