package stepdefinitions;

import io.cucumber.java.en.Given;
import utils.BrowserDriver;
import utils.ConfigReader;

public class BaseSteps {

    @Given("^open TKC homepage$")
    public void open_TKC_homepage() {
        BrowserDriver.getDriver().get(ConfigReader.getUrl("TKC"));
    }
}
