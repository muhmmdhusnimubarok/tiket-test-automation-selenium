package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utils.BrowserDriver;
import utils.ConfigReader;

public class FlightSteps {

    @Given("^open TKC home page$")
    public void open_TKC_home_page() {
        BrowserDriver.getDriver().get(ConfigReader.getUrl("TKC"));
    }

    @Then("^navigate to the flight page$")
    public void navigate_to_the_flight_page() {

    }
}
