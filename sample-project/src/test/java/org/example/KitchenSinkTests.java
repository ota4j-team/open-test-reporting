package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.abort;

@Tag("inherited")
public class KitchenSinkTests {

    @SuppressWarnings("JUnitMalformedDeclaration")
    @BeforeAll
    static void beforeAll(TestReporter reporter) {
        reporter.publishEntry("beforeAll");
    }

    @Test
    @DisplayName("successful test")
    @Tag("success")
    void successful(TestReporter reporter) {
        reporter.publishEntry("✅");
    }

    @Test
    @Disabled("for demonstration purposes")
    @Tag("aborted")
    void skipped() {
    }

    @Test
    @Tag("aborted")
    void aborted(TestReporter reporter) {
        reporter.publishEntry("foo", "bar");
        reporter.publishEntry("baz", "qux");

        abort("for demonstration purposes");
    }

    @Test
    @Tag("failure")
    void failed(TestReporter reporter) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("foo", "bar");
        map.put("baz", "qux");
        reporter.publishEntry(map);

        assertEquals("foo", "bar");
    }
}
