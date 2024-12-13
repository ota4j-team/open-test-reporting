package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.TestReporter;

import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.Map;

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
    void failed(TestReporter reporter) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("foo", "bar");
        map.put("baz", "qux");
        reporter.publishEntry(map);

        reporter.publishFile("test.txt", file -> Files.write(file, singletonList("Hello, World!")));

        System.out.println("Hello, world!");

        assertEquals("foo", "bar");
    }
}
