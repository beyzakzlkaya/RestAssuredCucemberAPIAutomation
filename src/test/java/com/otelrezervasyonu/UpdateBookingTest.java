package com.otelrezervasyonu;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UpdateBookingTest extends BaseTest{

    @Test
    public void updateBookingTests() {

        Response response= given()
                .contentType(ContentType.JSON)
                .header("Cookie","token="+createToken())
                .body(bookingObject("Ali","Subaşı",2345,false))
                .put("https://restful-booker.herokuapp.com/booking/"+createBookingId());

        response.prettyPrint();

        String firstName= response.jsonPath().getJsonObject("firstname");
        String lastName= response.jsonPath().getJsonObject("lastname");
        int totalPrice= response.jsonPath().getJsonObject("totalprice");

        Assertions.assertEquals("Ali",firstName);
        Assertions.assertEquals("Subaşı",lastName);
        Assertions.assertEquals(2345,totalPrice);
        Assertions.assertEquals(false,response.jsonPath().getJsonObject("depositpaid"));
    }

}

