package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductsAssertion {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    //1.Verify the if the total is equal to 51957
    @Test
    public void Test001() {
        response.body("total", equalTo(51957));
    }

    //2.Verify the if the stores of limit is equal to 10
    @Test
    public void Test002() {
        response.body("limit", equalTo(10));
    }

    //3.Check the single ‘Name’ in the Array list (Duracell - AAA Batteries (4-Pack))
    @Test
    public void Test003() {
        response.body("data.name", hasItem("Duracell - AAA Batteries (4-Pack)"));
    }

    //4.Check the multiple ‘Names’ in the ArrayList (Duracell - AA 1.5V CopperTop Batteries (4-Pack), Duracell - AA Batteries (8-Pack), Energizer - MAX Batteries AA (4-Pack))
    @Test
    public void Test004() {
        response.body("data.name", hasItems("Duracell - AA 1.5V CopperTop Batteries (4-Pack)", "Duracell - AA Batteries (8-Pack)", "Energizer - MAX Batteries AA (4-Pack)"));

    }

    //5.Verify the productId=150115 inside categories of the third name is “Household Batteries”
    @Test
    public void Test005() {
        response.body("data.findAll{it.categories[2].name == 'Household Batteries'}",hasItem(hasEntry("id",150115)));
    }

    //6.Verify the categories second name = “Housewares” of productID = 333179
    @Test
    public void Test006() {
        response.body("data.findAll{it.categories[1].name == 'Housewares'}",hasItem(hasEntry("id",333179)));
    }

    //7.Verify the price = 4.99 of forth product
    @Test
    public void Test007() {

        response.body("data[3].price",equalTo(Float.parseFloat("4.99")));
    }


    //8.Verify the Product name = Duracell - D Batteries (4-Pack) of 6th product
    @Test
    public void Test008() {
        response.body("data[5].name", equalTo("Duracell - D Batteries (4-Pack)"));
    }

    //9.Verify the ProductId = 333179 for the 9th product
    @Test
    public void Test009() {
        response.body("data[8].id", equalTo(333179));
    }

    //10.Verify the prodctId = 346575 have 5 categories
    @Test
    public void Test010() {
        response.body("data.findAll{it.categories.size == 5}",hasItem(hasEntry("id",346575)));
    }

}
