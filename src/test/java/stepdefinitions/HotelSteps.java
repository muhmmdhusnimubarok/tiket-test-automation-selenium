package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import utils.Hooks;

public class HotelSteps {

    //Given Section

    @Given("^navigate to hotel page$")
    public void navigate_to_hotel_page() {
        Hooks.getHotel().clickHotelMenu();
    }

    @When("^choose (.*) to stay$")
    public void choose_region_to_stay(String region) {
        Hooks.getHotel().setRegionToStay(region);
    }

    @When("^select (.*) and (.*) date$")
    public void select_checkin_and_checkout_date(String checkIn, String checkOut) {
        Hooks.getHotel().selectCheckinAndCheckoutDate( checkIn, checkOut);
    }

    @When("^select an available hotel$")
    public void select_an_available_hotel() {
        Hooks.getHotel().selectHotel();
    }

    @When("^submit guest personal informations$")
    public void submit_guest_personal_informations() {
        Hooks.getHotel().detailPemesan();
        Hooks.getHotel().detailTamu();
        Hooks.getHotel().pelengkapMenginap();
    }

    @When("^confirm book room order$")
    public void confirm_book_room_order() {
        Hooks.getHotel().validatePaymentPage();
    }
}
