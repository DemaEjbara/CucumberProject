package Runner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features ={"src/test/resources/features/PurchaseSingleItem.feature","src/test/resources/features/problem.feature"} ,
        glue = "DefinitionSteps",
        plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
)
public class TestRun {

}
