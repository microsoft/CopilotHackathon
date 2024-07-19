package com.microsoft.hackathon.quarkus;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class DemoResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello?key=world")
          .then()
             .statusCode(200)
             .body(is("hello world"));
    }

    @Test
    public void testHelloEndpointNoKey() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("key not passed"));
    }

    @Test
    public void testDiffDatesEndpoint() {
        given()
          .when().get("/diffdates?date1=01-01-2021&date2=01-02-2021")
          .then()
             .statusCode(200)
             .body(is("31"));
    }

    @Test
    public void testDiffDatesEndpointInvalidDate() {
        given()
          .when().get("/diffdates?date1=01-01201&date2=01-02-2021")
          .then()
             .statusCode(200)
             .body(is("invalid date format"));
    }

    @Test
    public void testDiffDatesEndpointNoDate1() {
        given()
          .when().get("/diffdates?date2=01-02-2021")
          .then()
             .statusCode(500);
    }

    @Test
    public void testDiffDatesEndpointNoDate2() {
        given()
          .when().get("/diffdates?date1=01-02-2021")
          .then()
             .statusCode(500);
    }

    @Test
    public void testDiffDatesEndpointNoDates() {
        given()
          .when().get("/diffdates")
          .then()
             .statusCode(500);
    }

    @Test
    public void testValidatePhoneEndpoint() {
        given()
          .when().get("/validatephone?phone=+34666666666")
          .then()
             .statusCode(200)
             .body(is("true"));
        
        given()
          .when().get("/validatephone?phone=+34766666666")
          .then()
             .statusCode(200)
             .body(is("true"));

        given()
          .when().get("/validatephone?phone=+34966666666")
          .then()
             .statusCode(200)
             .body(is("true"));
    }

    @Test
    public void testValidatePhoneEndpointInvalidPhone() {
        given()
          .when().get("/validatephone?phone=+3466666666")
          .then()
             .statusCode(200)
             .body(is("false"));
    }

    @Test
    public void testValidatePhoneEndpointNoPhone() {
        given()
          .when().get("/validatephone")
          .then()
             .statusCode(500);
    }


    @Test
    public void testValidatePhoneEndpointNoPrefix() {
        given()
          .when().get("/validatephone?phone=666666666")
          .then()
             .statusCode(200)
             .body(is("false"));
    }

    @Test
    public void testValidateDNIEndpoint () {
        given()
          .when().get("/validatedni?dni=12345678Z")
          .then()
             .statusCode(200)
             .body(is("true"));
    }

    @Test
    public void testValidateDNIEndpointInvalidDNI () {
        given()
          .when().get("/validatedni?dni=12345678")
          .then()
             .statusCode(200)
             .body(is("false"));
    }

    @Test
    public void testValidateDNIEndpointNoDNI () {
        given()
          .when().get("/validatedni")
          .then()
             .statusCode(500);
    }

   @Test
    public void testGetHexColorEndpoint () {
        given()
          .when().get("/hexcolor?name=red")
          .then()
             .statusCode(200)
             .body(is("#FF0000"));
    }

    @Test
    public void testGetHexColorEndpointInvalidColor () {
        given()
          .when().get("/hexcolor?name=red1")
          .then()
             .statusCode(404);
    }

    @Test
    public void testGetHexColorEndpointNoColor () {
        given()
          .when().get("/hexcolor")
          .then()
             .statusCode(500);
    }

    @Test
    public void testChuckNorrisEndpoint () {
        given()
          .when().get("/chucknorris")
          .then()
             .statusCode(200);
    }

    @Test
    public void testParseUrlEndpoint () {
        given()
          .when().get("/parseurl?url=https://learn.microsoft.com/en-us/azure/aks/concepts-clusters-workloads?source=recommendations")
          .then()
             .statusCode(200)
              .body("protocol", is("https"))
              .body("host", is("learn.microsoft.com"))
              .body("port", is(-1))
              .body("path", is("/en-us/azure/aks/concepts-clusters-workloads"))
              .body("query", is("source=recommendations"));
    }

    @Test
    public void testParseUrlEndpointNoUrl () {
        given()
          .when().get("/parseurl")
          .then()
             .statusCode(500);
    }

}