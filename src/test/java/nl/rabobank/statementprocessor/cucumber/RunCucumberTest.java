package nl.rabobank.statementprocessor.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by rtbrake on 7-11-16.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features")
public class RunCucumberTest {
}
