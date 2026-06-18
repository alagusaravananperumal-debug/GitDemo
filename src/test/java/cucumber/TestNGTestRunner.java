package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//Using TestNG only for running cucumber
//feature file links with "StepDefinitionImpl" we need to mention using "glue"
// "src\\test\\java\\cucumber" location of the future files
//"monochrome" Gives the results in readable format
//"html:target/cucumber.html" generate the resport in html plugin
//"AbstractTestNGCucumberTests" Cucumber understand and run the code where TestNG is there
//If you want to run only "ErrorValidations.feature" then we need to run through "tags"

@CucumberOptions(features = "src\\test\\java\\cucumber", glue = "rahulshettyacademy.stepDefinition", monochrome = true, tags = "@ErrorValidation", plugin = {
		"html:target/cucumber.html" })

public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
