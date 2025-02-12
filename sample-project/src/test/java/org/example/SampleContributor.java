package org.example;

import org.opentest4j.reporting.tooling.spi.htmlreport.Contributor;
import org.opentest4j.reporting.tooling.spi.htmlreport.Section;

import java.util.List;

public class SampleContributor implements Contributor {
    @Override
    public List<Section> contributeSectionsForExecution(Context context) {
        return List.of();
    }

    @Override
    public List<Section> contributeSectionsForTestNode(Context context) {
        return List.of();
    }
}
