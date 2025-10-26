package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import utils.Hooks;

public class FlightSteps {

    //Given Section

    @Given("^navigate to the flight page$")
    public void navigate_to_the_flight_page() {
        Hooks.getFlight().clickFlightMenu();
    }



    //When Section

    @When("^choose (.*) and (.*)$")
    public void choose_departure_and_destination(String departure, String destination) {
        System.out.println("DEBUG: choose_departure_and_destination called");
        Hooks.getFlight().setDepartureAndDestination(departure, destination);
    }

    @When("^select round trip$")
    public void select_round_trip() {
        Hooks.getFlight().clickRoundTripButton();
    }

   @When("^select (.*) of flight$")
   public void select_date_of_flight(String date) {
        Hooks.getFlight().selectFlightDateOneWay(date);
   }

   @When("^set (.*) of flight and (.*) of flight$")
   public void set_departuredate_of_flight_and_returndate_of_flight(String departureDate, String returnDate) {
        Hooks.getFlight().selectDFlightDateRoundTrip(departureDate, returnDate);
   }

   @When("^set flight class to (.*)$")
   public void set_flight_class_to(String flightClass) {
        Hooks.getFlight().selectFlightClass(flightClass);
   }

   @When("^set number of passengers and flight class to (.*)$")
   public void set_number_of_passengers_and_flight_class_to(String flightClass) {
       Hooks.getFlight().setPassangersAndFlightClass(flightClass);
   }

   @When("^select an available airline$")
   public void select_an_available_airlline() {
        Hooks.getFlight().selectFlightCard();
   }

   @When("^select an available airlines$")
   public void select_an_available_airlines() {
        Hooks.getFlight().selectFlightCards();
   }

   @When("^submit passenger personal informations$")
   public void submit_passenger_personal_informations() {
        Hooks.getFlight().detailPemesan();
        Hooks.getFlight().detailPenumpang1();
   }

   @When("^submit personal informations for all passengers$")
   public void submit_personal_informations_for_all_passengers() {
        Hooks.getFlight().detailPemesan();
        Hooks.getFlight().detailPenumpang1();
        Hooks.getFlight().detailPenumpang2();
        Hooks.getFlight().detailPenumpang3();
   }

   @When("^add extra luggage$")
   public void extra_luggage() {
        Hooks.getFlight().tambahkanBagasiSolo();
   }

   @When("^add extra luggage for all passengers$")
   public void add_extra_luggage_for_all_passengers() {
        Hooks.getFlight().tambahkanBagasiGroup();
   }

   @When("^add food$")
   public void add_food() {
        Hooks.getFlight().tambahkanMakananSolo();
   }

   @When("^add food for all passengers$")
   public void add_food_for_all_passengers() {
        Hooks.getFlight().tambahkanMakananGroup();
   }

   @When("^choose seat$")
   public void choose_seat() {
        Hooks.getFlight().pilihKursiSolo();
   }

   @When("^choose seat for all passengers$")
   public void choose_seat_for_all_passengers() {
        Hooks.getFlight().pilihKursiGroup();
   }

    @When("^add insurance$")
    public void add_insurance() {
        Hooks.getFlight().tambahkanAsuransi();

    }

   @When("^confirm book flight order$")
   public void confirm_book_flight_order() {
        Hooks.getFlight().lanjutBayar();
   }



   //Then Section

   @Then("^validate payment page$")
   public void validate_to_payment_page() {
        Hooks.getFlight().validatePaymentPage();
   }

}
