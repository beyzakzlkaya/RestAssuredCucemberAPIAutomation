package com.otelrezervasyonu;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class BaseTest {
    protected Response createBooking(){
        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .body(bookingObject())
                .post("https://restful-booker.herokuapp.com/booking");

        response.prettyPrint();

        response
                .then()
                .statusCode(200);
        return response;
    }
    protected String bookingObject(){
        JSONObject body = new JSONObject();
        body.put("firstname","Beyza");
        body.put("lastname","Kızılkaya");
        body.put("totalprice",765);
        body.put("depositpaid",false);


        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin","2023-01-01");
        bookingDates.put("checkout","2024-01-01");

        body.put("bookingdates",bookingDates);
        body.put("additionalneeds","Ekstra İçecek");

        return body.toString();
    }
}
