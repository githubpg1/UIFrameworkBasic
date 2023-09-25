package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(tags="not @ignore",features="src/test/java/features",glue="stepDefinations")
public class TestRunner {

	
}
