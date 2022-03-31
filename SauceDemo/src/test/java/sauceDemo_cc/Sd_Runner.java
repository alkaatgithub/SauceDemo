package sauceDemo_cc;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		//to locate the feature file
		features = "src/test/java/sauceDemo_cc/sd.feature"
		//to locate the cucumber package
		,glue = {"sauceDemo_cc"}
		//to get the tagname of the feature file
		,tags = "@sauceDemo"
		)

public class Sd_Runner {

}
