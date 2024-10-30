const testResultStatusColor = (status: string) => {
  switch (status) {
    case 'SUCCESSFUL':
      return 'green'
    case 'ABORTED':
      return 'yellow'
    case 'SKIPPED':
      return 'gray'
    default:
      return 'red'
  }
}

export default testResultStatusColor