export type NodeUi = {
    collapsed: boolean
}

export type RootUi = {
    nodes: Record<string, NodeUi>
    showAborted: boolean
    showFailed: boolean
    showSkipped: boolean
    showSuccessful: boolean

    collapseAll: () => void
    expandAll: () => void
    toggleShowAborted: () => void
    toggleShowFailed: () => void
    toggleShowSkipped: () => void
    toggleShowSuccessful: () => void
    toggleNode: (id: string) => void

    isVisible: (statuses: string[]) => boolean
}
