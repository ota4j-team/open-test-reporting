/**
 * Java API for validating both formats, converting from the event-based to the
 * hierarchical format (suitable for inclusion in build tools and reporting
 * tools), and generating HTML reports
 *
 * @since 0.2.0
 */
module org.opentest4j.reporting.tooling.core {

    requires static org.apiguardian.api;
    requires com.google.gson;
    requires org.opentest4j.reporting.events;
    requires org.opentest4j.reporting.tooling.spi;

    exports org.opentest4j.reporting.tooling.core.converter;
    exports org.opentest4j.reporting.tooling.core.htmlreport;
    exports org.opentest4j.reporting.tooling.core.validator;

    uses org.opentest4j.reporting.tooling.spi.htmlreport.Contributor;

    provides org.opentest4j.reporting.tooling.spi.htmlreport.Contributor with
            org.opentest4j.reporting.tooling.core.htmlreport.CoreContributor,
            org.opentest4j.reporting.tooling.core.htmlreport.GitContributor,
            org.opentest4j.reporting.tooling.core.htmlreport.JavaContributor;
}
