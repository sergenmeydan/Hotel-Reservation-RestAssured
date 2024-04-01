package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.BookingResponse;
import org.junit.jupiter.api.Assertions;
import service.ReservationService;

public class ReservationSteps {

    ReservationService reservationService;
    String authKey;

    BookingResponse bookingResponse;

    @Given("Creating a new reservation")
    public void newReservation(){
        reservationService = new ReservationService();
    }


    @Given("I provide the necessary information for booking")
    public void authKey(){
        authKey = reservationService.createToken();
    }


    @When("Creating my hotel reservation")
    public void creatingReservation(){
        bookingResponse = reservationService.createBooking();
    }


    @Then("My reservation was successfully created")
    public void reservationAssertions(){
        Assertions.assertEquals("Sergen", bookingResponse.getBooking().getFirstname());
        Assertions.assertEquals("Meydan", bookingResponse.getBooking().getLastname());
        Assertions.assertEquals(3000, bookingResponse.getBooking().getTotalprice());
        Assertions.assertFalse(bookingResponse.getBooking().isDepositpaid());
        Assertions.assertEquals("Breakfast", bookingResponse.getBooking().getAdditionalneeds());
    }


    @Then("I cancel the reservation I created")
    public void cancelReservation(){
        reservationService.deleteReservation(authKey, bookingResponse.getBookingid());
    }

}
