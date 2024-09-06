package com.otelrezervasyonu;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;



public class PostCreateBookingTests extends BaseTest {
    @Test
    public void postCreateBookingTests(){

        Response response = createBooking();

        Assertions.assertEquals("Beyza",response.jsonPath().getJsonObject("booking.firstname"));
        Assertions.assertEquals("Kızılkaya",response.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals(765,(Integer) response.jsonPath().getJsonObject("booking.totalprice"));



    }
}
