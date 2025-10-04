package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.types.Hook;
import utils.BrowserDriver;
import utils.ConfigReader;
import utils.Hooks;

public class FlightSteps {

    //Given Section

    @Given("^open TKC home page$")
    public void open_TKC_home_page() {
        BrowserDriver.getDriver().get(ConfigReader.getUrl("TKC"));
    }

    @Given("^navigate to the flight page$")
    public void navigate_to_the_flight_page() {
        Hooks.getFlight().clickFlightMenu();
    }



    //When Section

    @When("^choose (.*) and (.*)$")
    public void choose_departure_and_destination(String departure, String destination) {
        System.out.println("DEBUG: choose_departure_and_destination called");
        Hooks.getFlight().setDeparture(departure);
        Hooks.getFlight().setDestination(destination);
    }

   @When("^select (.*) of flight$")
   public void select_date_of_flight(String date) {
        Hooks.getFlight().selectFlightDateOneWay(date);
   }

   @When("^select an available airline$")
   public void select_an_available_airlline() {
        Hooks.getFlight().selectFlightCard();
   }

   @When("^reject flight disturbance protection$")
   public void reject_flight_disturbance_protection() {
        Hooks.getFlight().rejectFlightProtection();
   }

   @When("^submit personal information$")
   public void submit_personal_information() {
        Hooks.getFlight().detailPemesan();
        Hooks.getFlight().detailPenumpang1();
        Hooks.getFlight().batalkanAsuransi();
        Hooks.getFlight().lanjutBayar();
   }

   @When("^confirm order$")
   public void confirm_order() {
        Hooks.getFlight().lanjutKePembayaran();
   }

   @When("^select round trip$")
   public void select_round_trip() {
        Hooks.getFlight().clickRoundTripButton();
   }

   @When("^set (.*) of flight and (.*) of flight$")
   public void set_departuredate_of_flight_and_returndate_of_flight(String departureDate, String returnDate) {
        Hooks.getFlight().selectDFlightDateRoundTrip(departureDate, returnDate);
   }



   //Then Section

   @Then("^validate payment page$")
   public void validate_to_payment_page() {
        Hooks.getFlight().validatePaymentPage();
   }


}
