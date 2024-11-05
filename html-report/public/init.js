globalThis.testExecutions = [{ "id": "1", "name": "Execution", "durationMillis": 419, "sections": [{ "title": "Infrastructure", "blocks": [{ "type": "kvp", "content": { "Hostname": "coriol", "Username": "marc", "Operating system": "Linux", "CPU cores": "16" } }] }, { "title": "Java Virtual Machine", "blocks": [{ "type": "kvp", "content": { "Java version": "17.0.12", "File encoding": "UTF-8", "Max heap size": "512 MiB (536870912 bytes)" } }] }], "roots": ["2"], "children": { "2": ["3", "7", "11"], "3": ["4", "5", "6"], "7": ["8", "9", "10"], "11": ["12", "13", "14", "15"] }, "testNodes": [{ "id": "2", "name": "JUnit Jupiter", "durationMillis": 419, "status": "SUCCESSFUL" }, { "id": "3", "name": "DefaultConverterTests", "durationMillis": 123, "status": "SUCCESSFUL", "sections": [{ "title": "Sources", "blocks": [{ "type": "sub", "content": [{ "title": "Class", "blocks": [{ "type": "kvp", "content": { "className": "org.opentest4j.reporting.tooling.converter.DefaultConverterTests" } }] }] }] }] }, { "id": "4", "name": "convertsInfrastructureSection()", "durationMillis": 73, "status": "SUCCESSFUL", "sections": [{ "title": "Sources", "blocks": [{ "type": "sub", "content": [{ "title": "Method", "blocks": [{ "type": "kvp", "content": { "className": "org.opentest4j.reporting.tooling.converter.DefaultConverterTests", "methodName": "convertsInfrastructureSection" } }] }] }] }] }, { "id": "5", "name": "convertsStartedAndFinishedEvents()", "durationMillis": 20, "status": "SUCCESSFUL", "sections": [{ "title": "Sources", "blocks": [{ "type": "sub", "content": [{ "title": "Method", "blocks": [{ "type": "kvp", "content": { "className": "org.opentest4j.reporting.tooling.converter.DefaultConverterTests", "methodName": "convertsStartedAndFinishedEvents" } }] }] }] }] }, { "id": "6", "name": "mergesReportEntries()", "durationMillis": 16, "status": "SUCCESSFUL", "sections": [{ "title": "Sources", "blocks": [{ "type": "sub", "content": [{ "title": "Method", "blocks": [{ "type": "kvp", "content": { "className": "org.opentest4j.reporting.tooling.converter.DefaultConverterTests", "methodName": "mergesReportEntries" } }] }] }] }] }, { "id": "7", "name": "DefaultHtmlReportWriterTest", "durationMillis": 139, "status": "SUCCESSFUL", "sections": [{ "title": "Sources", "blocks": [{ "type": "sub", "content": [{ "title": "Class", "blocks": [{ "type": "kvp", "content": { "className": "org.opentest4j.reporting.tooling.htmlreport.DefaultHtmlReportWriterTest" } }] }] }] }] }, { "id": "8", "name": "aborted()", "durationMillis": 4, "status": "ABORTED", "sections": [{ "title": "Sources", "blocks": [{ "type": "sub", "content": [{ "title": "Method", "blocks": [{ "type": "kvp", "content": { "className": "org.opentest4j.reporting.tooling.htmlreport.DefaultHtmlReportWriterTest", "methodName": "aborted" } }] }] }] }, { "title": "Result", "blocks": [{ "type": "sub", "content": [{ "title": "org.opentest4j.TestAbortedException", "blocks": [{ "type": "pre", "content": "org.opentest4j.TestAbortedException: message\n\tat org.junit.jupiter.api.Assumptions.abort(Assumptions.java:295)\n\tat org.opentest4j.reporting.tooling.htmlreport.DefaultHtmlReportWriterTest.aborted(DefaultHtmlReportWriterTest.java:42)\n\tat java.base/java.lang.reflect.Method.invoke(Method.java:569)\n\tat java.base/java.util.ArrayList.forEach(ArrayList.java:1511)\n\tat java.base/java.util.ArrayList.forEach(ArrayList.java:1511)\n" }] }] }] }] }, { "id": "9", "name": "Write sample HTML report", "durationMillis": 126, "status": "SUCCESSFUL", "sections": [{ "title": "Tags", "blocks": [{ "type": "labels", "content": ["demo", "html-report", "tooling"] }] }, { "title": "Sources", "blocks": [{ "type": "sub", "content": [{ "title": "Method", "blocks": [{ "type": "kvp", "content": { "className": "org.opentest4j.reporting.tooling.htmlreport.DefaultHtmlReportWriterTest", "methodName": "writeSampleHtmlReport", "methodParameterTypes": "org.junit.jupiter.api.TestReporter" } }] }] }] }, { "title": "Attachments", "blocks": [{ "type": "sub", "content": [{ "title": "Data", "blocks": [{ "type": "kvp", "content": { "time": "2024-11-05T09:10:15.790185506", "value": "Start" } }] }, { "title": "Data", "blocks": [{ "type": "kvp", "content": { "time": "2024-11-05T09:10:15.913687110", "eventsXml": "build/test-results/test/hierarchy.xml", "targetFile": "build/test-results/test/hierarchy.html" } }] }] }] }] }, { "id": "10", "name": "skipped()", "durationMillis": 1, "status": "SKIPPED", "sections": [{ "title": "Sources", "blocks": [{ "type": "sub", "content": [{ "title": "Method", "blocks": [{ "type": "kvp", "content": { "className": "org.opentest4j.reporting.tooling.htmlreport.DefaultHtmlReportWriterTest", "methodName": "skipped" } }] }] }] }, { "title": "Result", "blocks": [{ "type": "sub", "content": [{ "title": "Reason", "blocks": [{ "type": "p", "content": "Disabled on JRE version: 17.0.12" }] }] }] }] }, { "id": "11", "name": "DefaultValidatorTests", "durationMillis": 139, "status": "SUCCESSFUL", "sections": [{ "title": "Sources", "blocks": [{ "type": "sub", "content": [{ "title": "Class", "blocks": [{ "type": "kvp", "content": { "className": "org.opentest4j.reporting.tooling.validator.DefaultValidatorTests" } }] }] }] }] }, { "id": "12", "name": "validatesEventsXmlFile()", "durationMillis": 39, "status": "SUCCESSFUL", "sections": [{ "title": "Sources", "blocks": [{ "type": "sub", "content": [{ "title": "Method", "blocks": [{ "type": "kvp", "content": { "className": "org.opentest4j.reporting.tooling.validator.DefaultValidatorTests", "methodName": "validatesEventsXmlFile" } }] }] }] }] }, { "id": "13", "name": "generatesWarningsAndErrors()", "durationMillis": 83, "status": "SUCCESSFUL", "sections": [{ "title": "Sources", "blocks": [{ "type": "sub", "content": [{ "title": "Method", "blocks": [{ "type": "kvp", "content": { "className": "org.opentest4j.reporting.tooling.validator.DefaultValidatorTests", "methodName": "generatesWarningsAndErrors" } }] }] }] }] }, { "id": "14", "name": "resolvesThirdPartySchemasUsingCatalog()", "durationMillis": 5, "status": "SUCCESSFUL", "sections": [{ "title": "Sources", "blocks": [{ "type": "sub", "content": [{ "title": "Method", "blocks": [{ "type": "kvp", "content": { "className": "org.opentest4j.reporting.tooling.validator.DefaultValidatorTests", "methodName": "resolvesThirdPartySchemasUsingCatalog" } }] }] }] }] }, { "id": "15", "name": "validatesHierarchicalXmlFile()", "durationMillis": 6, "status": "SUCCESSFUL", "sections": [{ "title": "Sources", "blocks": [{ "type": "sub", "content": [{ "title": "Method", "blocks": [{ "type": "kvp", "content": { "className": "org.opentest4j.reporting.tooling.validator.DefaultValidatorTests", "methodName": "validatesHierarchicalXmlFile" } }] }] }] }] }] }];
