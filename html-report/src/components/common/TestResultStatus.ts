export const testResultStatusForegroundColorClass = (status: string) => {
  switch (status) {
    case "SUCCESSFUL":
      return "text-green-600";
    case "ABORTED":
      return "text-yellow-600";
    case "SKIPPED":
      return "text-sky-600";
    default:
      return "text-red-600";
  }
};

export const testResultStatusBackgroundColorClasses = (status: string) => {
  switch (status) {
    case "SUCCESSFUL":
      return [
        "border-green-600",
        "bg-green-500",
        "dark:border-green-500",
        "dark:bg-green-600",
      ];
    case "ABORTED":
      return [
        "border-yellow-600",
        "bg-yellow-500",
        "dark:border-yellow-500",
        "dark:bg-yellow-600",
      ];
    case "SKIPPED":
      return [
        "border-sky-600",
        "bg-sky-500",
        "dark:border-sky-500",
        "dark:bg-sky-600",
      ];
    default:
      return [
        "border-red-600",
        "bg-red-500",
        "dark:border-red-500",
        "dark:bg-red-600",
      ];
  }
};
