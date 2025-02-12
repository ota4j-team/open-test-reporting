/**
 * Command-line interface (CLI) for validating both XML formats, converting from
 * the event-based to the hierarchical format, and generating HTML reports
 *
 * @since 0.2.0
 */
module org.opentest4j.reporting.cli {

    requires static org.apiguardian.api;
    requires info.picocli;
    requires java.desktop;
    requires org.opentest4j.reporting.tooling.core;
    requires org.slf4j;

    exports org.opentest4j.reporting.cli;
}
