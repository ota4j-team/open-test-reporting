globalThis.testExecutions = [
  {
    id: "1",
    name: "open-test-report.xml",
    durationMillis: 61,
    sections: [
      {
        title: "Infrastructure",
        blocks: [
          {
            type: "kvp",
            content: {
              Hostname: "coriol",
              Username: "marc",
              "Operating system": "Linux",
              "CPU cores": "16",
            },
          },
        ],
      },
      {
        title: "Java Virtual Machine",
        blocks: [
          {
            type: "kvp",
            content: {
              "Java version": "21.0.5",
              "File encoding": "UTF-8",
              "Max heap size": "512 MiB (536870912 bytes)",
            },
          },
        ],
      },
      {
        title: "Git",
        blocks: [
          {
            type: "kvp",
            content: {
              Origin: "https://github.com/ota4j-team/open-test-reporting.git",
              Branch: "marc/relativize-links-to-file-attachments",
              "Commit hash": "6f8b2a81301e11e381cdcb2a172cdad08a48e213",
              Clean: "false",
            },
          },
          {
            type: "sub",
            content: [
              {
                title: "Status of working tree",
                blocks: [
                  {
                    type: "pre",
                    content: " M sample-project/build.gradle.kts",
                  },
                ],
              },
            ],
          },
        ],
      },
    ],
    roots: ["2"],
    children: {
      2: {
        ids: ["3"],
        childStatuses: ["FAILED", "SUCCESSFUL", "SKIPPED", "ABORTED"],
      },
      3: {
        ids: ["4", "5", "6", "7"],
        childStatuses: ["FAILED", "SUCCESSFUL", "SKIPPED", "ABORTED"],
      },
    },
    testNodes: [
      {
        id: "2",
        name: "JUnit Jupiter",
        durationMillis: 61,
        status: "SUCCESSFUL",
        sections: [],
      },
      {
        id: "3",
        name: "KitchenSinkTests",
        durationMillis: 45,
        status: "SUCCESSFUL",
        sections: [
          {
            title: "Tags",
            blocks: [{ type: "labels", content: ["inherited"] }],
          },
          {
            title: "Sources",
            blocks: [
              {
                type: "sub",
                content: [
                  {
                    title: "Class",
                    blocks: [
                      {
                        type: "kvp",
                        content: { className: "org.example.KitchenSinkTests" },
                      },
                    ],
                  },
                ],
              },
            ],
          },
          {
            title: "Attachments",
            blocks: [
              {
                type: "sub",
                content: [
                  {
                    title: "Data",
                    blocks: [
                      {
                        type: "kvp",
                        content: {
                          time: "2024-12-01T17:19:51.264725583",
                          value: "beforeAll",
                        },
                      },
                    ],
                  },
                ],
              },
            ],
          },
        ],
      },
      {
        id: "4",
        name: "successful test",
        durationMillis: 8,
        status: "SUCCESSFUL",
        sections: [
          {
            title: "Tags",
            blocks: [{ type: "labels", content: ["inherited", "success"] }],
          },
          {
            title: "Sources",
            blocks: [
              {
                type: "sub",
                content: [
                  {
                    title: "Method",
                    blocks: [
                      {
                        type: "kvp",
                        content: {
                          className: "org.example.KitchenSinkTests",
                          methodName: "successful",
                          methodParameterTypes:
                            "org.junit.jupiter.api.TestReporter",
                        },
                      },
                    ],
                  },
                ],
              },
            ],
          },
          {
            title: "Attachments",
            blocks: [
              {
                type: "sub",
                content: [
                  {
                    title: "Data",
                    blocks: [
                      {
                        type: "kvp",
                        content: {
                          time: "2024-12-01T17:19:51.272912809",
                          value: "✅",
                        },
                      },
                    ],
                  },
                ],
              },
            ],
          },
        ],
      },
      {
        id: "5",
        name: "skipped()",
        durationMillis: 0,
        status: "SKIPPED",
        sections: [
          {
            title: "Tags",
            blocks: [{ type: "labels", content: ["aborted", "inherited"] }],
          },
          {
            title: "Sources",
            blocks: [
              {
                type: "sub",
                content: [
                  {
                    title: "Method",
                    blocks: [
                      {
                        type: "kvp",
                        content: {
                          className: "org.example.KitchenSinkTests",
                          methodName: "skipped",
                        },
                      },
                    ],
                  },
                ],
              },
            ],
          },
          {
            title: "Reason",
            blocks: [{ type: "p", content: "for demonstration purposes" }],
          },
        ],
      },
      {
        id: "6",
        name: "aborted(TestReporter)",
        durationMillis: 4,
        status: "ABORTED",
        sections: [
          {
            title: "Tags",
            blocks: [{ type: "labels", content: ["aborted", "inherited"] }],
          },
          {
            title: "Sources",
            blocks: [
              {
                type: "sub",
                content: [
                  {
                    title: "Method",
                    blocks: [
                      {
                        type: "kvp",
                        content: {
                          className: "org.example.KitchenSinkTests",
                          methodName: "aborted",
                          methodParameterTypes:
                            "org.junit.jupiter.api.TestReporter",
                        },
                      },
                    ],
                  },
                ],
              },
            ],
          },
          {
            title: "Result",
            blocks: [
              {
                type: "sub",
                content: [
                  {
                    title: "org.opentest4j.TestAbortedException",
                    blocks: [
                      {
                        type: "pre",
                        content:
                          "org.opentest4j.TestAbortedException: for demonstration purposes\n\tat org.junit.jupiter.api.Assumptions.abort(Assumptions.java:300)\n\tat org.example.KitchenSinkTests.aborted(KitchenSinkTests.java:53)\n\tat java.base/java.lang.reflect.Method.invoke(Method.java:580)\n\tat java.base/java.util.ArrayList.forEach(ArrayList.java:1596)\n\tat java.base/java.util.ArrayList.forEach(ArrayList.java:1596)\n",
                      },
                    ],
                  },
                ],
              },
            ],
          },
          {
            title: "Attachments",
            blocks: [
              {
                type: "sub",
                content: [
                  {
                    title: "Data",
                    blocks: [
                      {
                        type: "kvp",
                        content: {
                          time: "2024-12-01T17:19:51.283432229",
                          foo: "bar",
                        },
                      },
                    ],
                  },
                  {
                    title: "Data",
                    blocks: [
                      {
                        type: "kvp",
                        content: {
                          time: "2024-12-01T17:19:51.283731827",
                          baz: "qux",
                        },
                      },
                    ],
                  },
                ],
              },
            ],
          },
        ],
      },
      {
        id: "7",
        name: "failed(TestReporter)",
        durationMillis: 7,
        status: "FAILED",
        sections: [
          {
            title: "Tags",
            blocks: [{ type: "labels", content: ["failure", "inherited"] }],
          },
          {
            title: "Sources",
            blocks: [
              {
                type: "sub",
                content: [
                  {
                    title: "Method",
                    blocks: [
                      {
                        type: "kvp",
                        content: {
                          className: "org.example.KitchenSinkTests",
                          methodName: "failed",
                          methodParameterTypes:
                            "org.junit.jupiter.api.TestReporter",
                        },
                      },
                    ],
                  },
                ],
              },
            ],
          },
          {
            title: "Result",
            blocks: [
              {
                type: "sub",
                content: [
                  {
                    title: "org.opentest4j.AssertionFailedError",
                    blocks: [
                      {
                        type: "pre",
                        content:
                          "org.opentest4j.AssertionFailedError: expected: \u003cfoo\u003e but was: \u003cbar\u003e\n\tat org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)\n\tat org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)\n\tat org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)\n\tat org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)\n\tat org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)\n\tat org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)\n\tat org.example.KitchenSinkTests.failed(KitchenSinkTests.java:67)\n\tat java.base/java.lang.reflect.Method.invoke(Method.java:580)\n\tat java.base/java.util.ArrayList.forEach(ArrayList.java:1596)\n\tat java.base/java.util.ArrayList.forEach(ArrayList.java:1596)\n",
                      },
                    ],
                  },
                ],
              },
            ],
          },
          {
            title: "Attachments",
            blocks: [
              {
                type: "sub",
                content: [
                  {
                    title: "Data",
                    blocks: [
                      {
                        type: "kvp",
                        content: {
                          time: "2024-12-01T17:19:51.289237149",
                          foo: "bar",
                          baz: "qux",
                        },
                      },
                    ],
                  },
                  {
                    title: "File",
                    blocks: [
                      {
                        type: "kvp",
                        content: {
                          time: "2024-12-01T17:19:51.291075833",
                          path: "link:junit-jupiter/org.example.KitchenSinkTests/failed(org.junit.jupiter.api.TestReporter)/test.txt",
                        },
                      },
                    ],
                  },
                ],
              },
            ],
          },
        ],
      },
    ],
  },
];
