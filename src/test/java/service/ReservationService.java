package service;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Auth;
import models.Booking;
import models.BookingDates;
import models.BookingResponse;

import static io.restassured.RestAssured.given;

public class ReservationService extends BaseTest {

    public String createToken(){

        Auth authBody = new Auth("admin","password123");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(authBody)
                .post("/auth");

        response
                .then()
                .statusCode(200);

        return response.jsonPath().getJsonObject("token");

    }

    public BookingResponse createBooking(){
        BookingDates bookingDates = new BookingDates("2024-04-01", "2024-04-30");
        Booking booking = new Booking("Sergen", "Meydan", 3000, false, bookingDates, "Breakfast");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(booking)
                .post("/booking");

        response
                .then()
                .statusCode(200);

        return response.as(BookingResponse.class);
    }

    public void deleteReservation(String token, int bookingId){

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie","token=" +token)
                .when()
                .delete("/booking/"+bookingId);

        response
                .then()
                .statusCode(201);
    }


}
