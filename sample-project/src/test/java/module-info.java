module org.example {
    requires org.junit.jupiter.api;
    opens org.example to org.junit.platform.commons;
    requires org.opentest4j.reporting.tooling.spi;
    provides org.opentest4j.reporting.tooling.spi.htmlreport.Contributor with org.example.SampleContributor;
}
