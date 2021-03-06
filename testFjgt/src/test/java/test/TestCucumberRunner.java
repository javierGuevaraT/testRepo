package test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"},
				 features = {"src/test/resources/features"},
				 glue = "com.unosquare.test.stepdefinitions")
public class TestCucumberRunner {
}
