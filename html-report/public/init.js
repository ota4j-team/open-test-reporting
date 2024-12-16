globalThis.testExecutions = [
  {
    id: "1",
    name: "open-test-report.xml",
    durationMillis: 67,
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
              Branch: "main",
              "Commit hash": "0766118523391cd55f8524a30a29d235f25ca31f",
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
                    content:
                      " M html-report/public/init.js\n M html-report/src/global.d.ts\n M settings.gradle.kts\n M tooling-core/src/main/java/org/opentest4j/reporting/tooling/core/htmlreport/CoreContributor.java\n M tooling-core/src/main/java/org/opentest4j/reporting/tooling/core/htmlreport/DefaultHtmlReportWriter.java\n M tooling-spi/src/main/java/org/opentest4j/reporting/tooling/spi/htmlreport/Section.java",
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
        durationMillis: 67,
        status: "SUCCESSFUL",
        sections: [
          {
            title: "JUnit metadata",
            blocks: [
              {
                type: "kvp",
                content: {
                  Type: "CONTAINER",
                  "Unique ID": "[engine:junit-jupiter]",
                  "Legacy reporting name": "JUnit Jupiter",
                },
              },
            ],
          },
        ],
      },
      {
        id: "3",
        name: "KitchenSinkTests",
        durationMillis: 54,
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
            title: "JUnit metadata",
            blocks: [
              {
                type: "kvp",
                content: {
                  Type: "CONTAINER",
                  "Unique ID":
                    "[engine:junit-jupiter]/[class:org.example.KitchenSinkTests]",
                  "Legacy reporting name": "org.example.KitchenSinkTests",
                },
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
                    metaInfo: "2024-12-16T09:27:14.762983214",
                    blocks: [{ type: "kvp", content: { value: "beforeAll" } }],
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
        durationMillis: 9,
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
            title: "JUnit metadata",
            blocks: [
              {
                type: "kvp",
                content: {
                  Type: "TEST",
                  "Unique ID":
                    "[engine:junit-jupiter]/[class:org.example.KitchenSinkTests]/[method:successful(org.junit.jupiter.api.TestReporter)]",
                  "Legacy reporting name": "successful(TestReporter)",
                },
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
                    metaInfo: "2024-12-16T09:27:14.772575463",
                    blocks: [{ type: "kvp", content: { value: "✅" } }],
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
            title: "JUnit metadata",
            blocks: [
              {
                type: "kvp",
                content: {
                  Type: "TEST",
                  "Unique ID":
                    "[engine:junit-jupiter]/[class:org.example.KitchenSinkTests]/[method:skipped()]",
                  "Legacy reporting name": "skipped()",
                },
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
        durationMillis: 5,
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
            title: "JUnit metadata",
            blocks: [
              {
                type: "kvp",
                content: {
                  Type: "TEST",
                  "Unique ID":
                    "[engine:junit-jupiter]/[class:org.example.KitchenSinkTests]/[method:aborted(org.junit.jupiter.api.TestReporter)]",
                  "Legacy reporting name": "aborted(TestReporter)",
                },
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
                    metaInfo: "2024-12-16T09:27:14.784819175",
                    blocks: [{ type: "kvp", content: { foo: "bar" } }],
                  },
                  {
                    title: "Data",
                    metaInfo: "2024-12-16T09:27:14.785359179",
                    blocks: [{ type: "kvp", content: { baz: "qux" } }],
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
        durationMillis: 10,
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
            title: "JUnit metadata",
            blocks: [
              {
                type: "kvp",
                content: {
                  Type: "TEST",
                  "Unique ID":
                    "[engine:junit-jupiter]/[class:org.example.KitchenSinkTests]/[method:failed(org.junit.jupiter.api.TestReporter)]",
                  "Legacy reporting name": "failed(TestReporter)",
                },
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
                          "org.opentest4j.AssertionFailedError: expected: \u003cfoo\u003e but was: \u003cbar\u003e\n\tat org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)\n\tat org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)\n\tat org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)\n\tat org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)\n\tat org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)\n\tat org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1145)\n\tat org.example.KitchenSinkTests.failed(KitchenSinkTests.java:70)\n\tat java.base/java.lang.reflect.Method.invoke(Method.java:580)\n\tat java.base/java.util.ArrayList.forEach(ArrayList.java:1596)\n\tat java.base/java.util.ArrayList.forEach(ArrayList.java:1596)\n",
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
                    metaInfo: "2024-12-16T09:27:14.791735171",
                    blocks: [
                      { type: "kvp", content: { foo: "bar", baz: "qux" } },
                    ],
                  },
                  {
                    title: "File",
                    metaInfo: "2024-12-16T09:27:14.794006979",
                    blocks: [
                      {
                        type: "kvp",
                        content: {
                          Filename: "test.txt",
                          Path: "link:junit-jupiter/org.example.KitchenSinkTests/failed(org.junit.jupiter.api.TestReporter)/test.txt",
                        },
                      },
                    ],
                  },
                  {
                    title: "Standard output",
                    metaInfo: "2024-12-16T09:27:14.798762991",
                    blocks: [
                      { type: "pre", content: "some standard output\n" },
                    ],
                  },
                  {
                    title: "Standard error",
                    metaInfo: "2024-12-16T09:27:14.798762991",
                    blocks: [{ type: "pre", content: "some error output\n" }],
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
