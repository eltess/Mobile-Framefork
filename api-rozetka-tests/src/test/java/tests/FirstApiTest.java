package tests;

import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import rozetka.BodyData;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.StringContains.containsString;

public class FirstApiTest {

    @BeforeTest
    public void setFilter() {
        RestAssured.filters(new AllureRestAssured());
    }

    @Owner("Serhiy Lebediev")
    @Feature("Search Field Feature")
    @Severity(SeverityLevel.BLOCKER)
    @Description("This test checks the search functionality and its result")

    @Test
    public void firstApi() {

        given()
            .baseUri("https://postman-echo.com")
            .param("foo1", "bar1")
            .param("foo2", "bar2")

            .when()
            .get("/get")

            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .log()
            .body()
            .body(matchesJsonSchemaInClasspath("schemas/firstSchema.json"));
    }

    @Test
    public void firstApi2() {
        BodyData requestBodyData = BodyData.builder()
            .name("test name")
            .age(18)
            .hobby("sport")
            .build();

        JSONObject requestBody = new JSONObject()
            .put("name", "test name")
            .put("age", 18)
            .put("hobby", "sport");

        Response response = given()
            // .baseUri("https://httpbin.org")
            .spec(requestSpecification())

            .when()
            .body(requestBodyData)
            .post("/post")

            .then()
            .statusCode(200)
            .body("data", containsString("test name"))
            .body("headers.Content-Length", equalTo("45"))
            .extract().response();

        String origin = response.path("origin");
        System.out.println(origin);
    }

    private RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
            .setBaseUri("https://httpbin.org")
            .build().contentType(ContentType.JSON);
    }
}
