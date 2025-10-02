package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.BrowserDriver;
import utils.ConfigReader;
import utils.Hooks;

public class FlightSteps {

    @Given("^open TKC home page$")
    public void open_TKC_home_page() {
        BrowserDriver.getDriver().get(ConfigReader.getUrl("TKC"));
    }

    @Then("^navigate to the flight page$")
    public void navigate_to_the_flight_page() {
        Hooks.getFlight().clickFlightMenu();
    }

    @When("^choose (.*) and (.*)$")
    public void choose_departure_and_destination(String departure, String destination) {
        System.out.println("DEBUG: choose_departure_and_destination called");
        Hooks.getFlight().setDeparture(departure);
        Hooks.getFlight().setDestination(destination);
    }

   @When("^select (.*) of flight$")
   public void select_date_of_flight(String date) {
        Hooks.getFlight().selectFlightDate(date);
   }

   @When("^select an available airline$")
   public void select_an_available_airlline() {
    Hooks.getFlight().selectFlightCardNoInsurance();
    Hooks.getFlight().selectFareCard();
    Hooks.getFlight().clickNggaDulu();
   }

   @When("^fills personal information$")
   public void fills_personal_information() {
        Hooks.getFlight().detailPemesan();
   }
}
