package com.otelrezervasyonu;

import io.restassured.response.Response;
import org.codehaus.groovy.classgen.asm.AssertionWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetAllBookingTests extends BaseTest{
    @Test
    public void getAllBookingTests(){

        Response newBooking = createBooking();
        int reservastionId = newBooking.jsonPath().getJsonObject("bookingid");

        Response response = given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking/"+reservastionId);

        response
                .then()
                .statusCode(200);

        response.prettyPrint();

        String firstname = response.jsonPath().getJsonObject("firstname");
        String lastname = response.jsonPath().getJsonObject("lastname");
        int totalprice = response.jsonPath().get("totalprice");
        Assertions.assertEquals(765,totalprice);
        Assertions.assertEquals("Beyza",firstname);
        Assertions.assertEquals("Kızılkaya",lastname);
    }
}
