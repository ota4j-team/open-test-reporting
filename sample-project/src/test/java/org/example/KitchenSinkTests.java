package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.TestReporter;

import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.abort;

@Tag("inherited")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class KitchenSinkTests {

    @SuppressWarnings("JUnitMalformedDeclaration")
    @BeforeAll
    static void beforeAll(TestReporter reporter) {
        reporter.publishEntry("beforeAll");
    }

    @Test
    @DisplayName("successful test")
    @Tag("success")
    @Order(1)
    void successful(TestReporter reporter) {
        reporter.publishEntry("✅");
    }

    @Test
    @Disabled("for demonstration purposes")
    @Tag("aborted")
    @Order(2)
    void skipped() {
    }

    @Test
    @Tag("aborted")
    @Order(3)
    void aborted(TestReporter reporter) {
        reporter.publishEntry("foo", "bar");
        reporter.publishEntry("baz", "qux");

        abort("for demonstration purposes");
    }

    @Test
    @Tag("failure")
    @Order(4)
    void failed(TestInfo testInfo, TestReporter reporter) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("displayName", testInfo.getDisplayName());
        map.put("testClass", testInfo.getTestClass().orElseThrow().getName());
        map.put("testMethod", testInfo.getTestMethod().orElseThrow().toGenericString());
        reporter.publishEntry(map);

        MediaType mediaType = MediaType.create("text", "plain", ISO_8859_1);
        reporter.publishFile("test.txt", mediaType, file -> Files.write(file, singletonList("Hellö, Wörld!"), ISO_8859_1));

        System.out.println("some standard output");
        System.err.println("some error output");

        assertEquals("foo", "bar");
    }
}
