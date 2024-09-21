package com.otelrezervasyonu;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected int createBookingId(){
        Response response = createBooking();
        return response.jsonPath().getJsonObject("bookingid");
    }
    protected Response createBooking(){
        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .body(bookingObject("Nur","Kızılkaya",124,true))
                .post("https://restful-booker.herokuapp.com/booking");

        response.prettyPrint();

        response
                .then()
                .statusCode(200);
        return response;
    }
    protected String bookingObject(String firstName,String lastName, int totalPrice,boolean depositPaid){
        JSONObject body = new JSONObject();
        body.put("firstname",firstName);
        body.put("lastname",lastName);
        body.put("totalprice",totalPrice);
        body.put("depositpaid",false);

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin","2023-01-01");
        bookingDates.put("checkout","2024-01-01");

        body.put("bookingdates",bookingDates);
        body.put("additionalneeds","Ekstra İçecek");

        return body.toString();
    }

    protected  String createToken(){
        JSONObject body = new JSONObject();
        body.put("username","admin");
        body.put("password","password123");

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(body.toString())
                .log().all()
                .post("https://restful-booker.herokuapp.com/auth");

        response.prettyPrint();
        return response.jsonPath().getJsonObject("token");
    }
}
