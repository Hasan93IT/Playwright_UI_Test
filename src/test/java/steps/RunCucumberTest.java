package steps;

import org.junit.platform.suite.api.*;
import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty,html:reports/cucumber/report.html,json:reports/cucumber/report.json")

public class RunCucumberTest {
}
