import nmcp.NmcpAggregationExtension

plugins.apply("com.gradleup.nmcp.aggregation")

configure<NmcpAggregationExtension> {
    centralPortal {
        username = providers.gradleProperty("mavenCentralUsername")
        password = providers.gradleProperty("mavenCentralPassword")
        publishingType = providers.gradleProperty("mavenCentralPublishingType").orElse("USER_MANAGED")
    }
    publishAllProjectsProbablyBreakingProjectIsolation()
}
