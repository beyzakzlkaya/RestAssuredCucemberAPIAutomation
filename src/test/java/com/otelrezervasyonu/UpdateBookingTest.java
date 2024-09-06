package com.otelrezervasyonu;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UpdateBookingTest{

    @Test
    public void updateBookingTests(){
        createToken();
    }
    public void createToken(){
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



    }

}

