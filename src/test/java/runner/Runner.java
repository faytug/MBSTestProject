package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        plugin = {"pretty",
                "json:target/json-report/cucumber.json",
                "html:target/cucumber-reports.json",
                "junit:target/cucumber-reports/Cucumber.xml",
        },

        features = "src/test/resources/features",
        glue = "stepdefinitions",
        tags = "@Negative",
        dryRun = false

)
public class Runner {


}